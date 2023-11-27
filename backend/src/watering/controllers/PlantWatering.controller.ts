import { Body, Controller, Param, Post } from '@nestjs/common';
import { PlantWateringDto } from '../dtos/PlantWatering.dto';
import { WateringService } from '../providers/Watering.service';
import { plainToInstance } from 'class-transformer';
import { PlantWateringStatusDto } from '../dtos/PlantWateringStatus.dto';
import { startOfToday } from 'date-fns';

@Controller('/plants/:plantId/watering')
export class PlantWateringController {
  constructor(private wateringService: WateringService) {}
  @Post()
  async waterPlant(
    @Param('plantId') plantId: string,
    @Body() plantWateringDto: PlantWateringDto,
  ): Promise<PlantWateringStatusDto> {
    return plainToInstance(
      PlantWateringStatusDto,
      await this.wateringService.waterPlant(
        +plantId,
        plantWateringDto.date
          ? new Date(plantWateringDto.date)
          : startOfToday(),
      ),
    );
  }
}
