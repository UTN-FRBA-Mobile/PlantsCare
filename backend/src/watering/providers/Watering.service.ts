import { Injectable, Module } from '@nestjs/common';
import { InjectRepository, TypeOrmModule } from '@nestjs/typeorm';
import { WateringHistory } from '../model/WateringHistory.entity';
import { Between, Repository, UpdateResult } from 'typeorm';
import { WateringByDateDto } from '../dtos/WateringByDate.dto';
import { addDays, eachDayOfInterval, startOfDay, subDays } from 'date-fns';
import { WateringHistoryStatus } from '../model/WateringHistoryStatus';

import { WateringController } from '../controllers/Watering.controller';
import { Plant } from '../../plants/model/Plant.entity';
import { PlantWateringStatusDto } from '../dtos/PlantWateringStatus.dto';
import { entityNotFoundError } from '../../utils/entities/entityNotFoundError';
import { id } from 'date-fns/locale';

@Injectable()
export class WateringService {
  constructor(
    @InjectRepository(WateringHistory)
    private wateringRepository: Repository<WateringHistory>,
  ) {}

  async getMonthOfWateringByDate(date: Date): Promise<WateringByDateDto[]> {
    const [start, end] = [
      subDays(startOfDay(date), 15),
      addDays(startOfDay(date), 15),
    ];
    const watering = await this.getWateringHistoryBetweenDates(start, end);
    return eachDayOfInterval({ start, end }).map((date) => ({
      date,
      plants: watering.get(date.getTime()) || [],
    }));
  }

  private getWateringHistoryBetweenDates(firstDate: Date, lastDate: Date) {
    return this.wateringRepository
      .find({
        where: {
          date: Between(firstDate, lastDate),
          status: WateringHistoryStatus.NEEDS_WATERING,
        },
        relations: ['plant'],
      })
      .then((watering) => this.getWateringHistoriesByDate(watering));
  }

  private getWateringHistoriesByDate(wateringHistories: WateringHistory[]) {
    return wateringHistories.reduce(
      (plantsByDate, history) =>
        plantsByDate.set(
          history.date.getTime(),
          setNewPlant(plantsByDate, history),
        ),
      new Map(),
    );

    function setNewPlant(
      plantsByDate: Map<number, Plant[]>,
      history: WateringHistory,
    ) {
      return (plantsByDate.get(history.date.getTime()) || []).concat(
        history.plant,
      );
    }
  }

  public waterPlant(
    plantId: number,
    date: Date,
  ): Promise<PlantWateringStatusDto> {
    return this.wateringRepository
      .update(
        { plant: { id: plantId }, date },
        { status: WateringHistoryStatus.WATERED },
      )
      .then(verifyUpdate);

    function verifyUpdate(update: UpdateResult) {
      return update.affected
        ? { date, status: WateringHistoryStatus.WATERED }
        : entityNotFoundError(
            Plant.name,
            `${plantId} and watering date ${date.toISOString()}`,
          );
    }
  }
}
