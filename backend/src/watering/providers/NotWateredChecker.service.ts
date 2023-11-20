import { InjectRepository } from '@nestjs/typeorm';
import { WateringHistory } from '../model/WateringHistory.entity';
import { In, Not, Repository } from 'typeorm';
import { Injectable } from '@nestjs/common';
import { Cron, CronExpression } from '@nestjs/schedule';
import { startOfToday, startOfTomorrow } from 'date-fns';
import { WateringHistoryStatus } from '../model/WateringHistoryStatus';

@Injectable()
export class NotWateredCheckerService {
  constructor(
    @InjectRepository(WateringHistory)
    private wateringRepository: Repository<WateringHistory>,
  ) {}

  @Cron(CronExpression.EVERY_DAY_AT_MIDNIGHT)
  async markAsNotWatered() {
    console.log(`marking as not watered since ${startOfToday().toISOString()}`);
    await this.wateringRepository.update(
      {
        status: WateringHistoryStatus.NEEDS_WATERING,
        date: startOfToday(),
      },
      { status: WateringHistoryStatus.NOT_WATERED },
    );

    const plantsId = await this.wateringRepository
      .find({
        where: {
          status: WateringHistoryStatus.NOT_WATERED,
          date: startOfToday(),
        },
      })
      .then((plants) => plants.map(({ plantId }) => plantId));

    await this.wateringRepository.update(
      {
        date: startOfTomorrow(),
        status: Not(WateringHistoryStatus.WATERED),
        plant: { id: In(plantsId) },
      },
      { status: WateringHistoryStatus.NEEDS_WATERING },
    );
  }
}
