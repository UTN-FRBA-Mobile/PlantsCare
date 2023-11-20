import { Body, Controller, Delete, Get, Param, Post } from '@nestjs/common';
import { PlantResponseDto } from '../dtos/PlantResponse.dto';
import { plainToInstance } from 'class-transformer';
import { PlantService } from '../providers/Plant.service';
import { CreatePlantDto } from '../dtos/CreatePlant.dto';
import { WateringHistoryStatus } from '../../watering/model/WateringHistoryStatus';
import * as wasi from 'wasi';
import { differenceInDays, startOfToday } from 'date-fns';

@Controller('/plants')
export class PlantController {
  constructor(private plantService: PlantService) {}

  @Get()
  async getPlants(): Promise<PlantResponseDto[]> {
    return plainToInstance(
      PlantResponseDto,
      await this.plantService.getAll(1),
    ).map((plant) => this.addNextWatering(plant));
  }

  @Get(':id')
  async getPlant(@Param('id') id: number): Promise<PlantResponseDto> {
    return this.addNextWatering(
      plainToInstance(
        PlantResponseDto,
        await this.plantService.getById(1, Number(id)),
      ),
    );
  }

  addNextWatering(plant: PlantResponseDto): PlantResponseDto {
    return {
      ...plant,
      nextWateringInDays: differenceInDays(
        this.getNextWater(plant).date,
        startOfToday(),
      ),
    };
  }

  private getNextWater(plant: PlantResponseDto) {
    return plant.history.find(
      (history) => history.status === WateringHistoryStatus.NEEDS_WATERING,
    );
  }

  @Post()
  async createPlant(
    @Body() plantDto: CreatePlantDto,
  ): Promise<PlantResponseDto> {
    return plainToInstance(
      PlantResponseDto,
      await this.plantService.create(1, plantDto),
    );
  }

  @Delete(':id')
  async deletePlant(@Param('id') id: number): Promise<void> {
    return this.plantService.delete(1, Number(id));
  }
}
