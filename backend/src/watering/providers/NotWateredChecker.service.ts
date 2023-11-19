import { InjectRepository } from '@nestjs/typeorm';
import { WateringHistory } from '../model/WateringHistory.entity';
import { LessThanOrEqual, Repository } from 'typeorm';
import { Injectable } from '@nestjs/common';
import { Cron, CronExpression } from '@nestjs/schedule';
import { startOfToday } from 'date-fns';
import { WateringHistoryStatus } from '../model/WateringHistoryStatus';

@Injectable()
export class NotWateredCheckerService {
  constructor(
    @InjectRepository(WateringHistory)
    private wateringRepository: Repository<WateringHistory>,
  ) {}

  @Cron(CronExpression.EVERY_DAY_AT_MIDNIGHT)
  markAsNotWatered() {
    console.log(`marking as not watered since ${startOfToday().toISOString()}`);
    return this.wateringRepository.update(
      {
        status: WateringHistoryStatus.NEEDS_WATERING,
        date: LessThanOrEqual(startOfToday()),
      },
      { status: WateringHistoryStatus.NOT_WATERED },
    );
  }
}
