import { Controller, Get, Query } from '@nestjs/common';
import { WateringByDateDto } from '../dtos/WateringByDate.dto';
import { plainToInstance } from 'class-transformer';
import { WateringService } from '../providers/Watering.service';

@Controller('/watering')
export class WateringController {
  constructor(private wateringService: WateringService) {}
  @Get()
  async getCurrentWatering(): Promise<WateringByDateDto[]> {
    return plainToInstance(
      WateringByDateDto,
      await this.wateringService.getMonthOfWateringByDate(new Date()),
    );
  }
}
