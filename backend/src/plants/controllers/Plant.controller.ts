import {
  Body,
  Controller,
  Delete,
  Get,
  Param,
  Post,
  Put,
} from '@nestjs/common';
import { PlantResponseDto } from '../dtos/PlantResponse.dto';
import { plainToInstance } from 'class-transformer';
import { PlantService } from '../providers/Plant.service';
import { CreatePlantDto } from '../dtos/CreatePlant.dto';
import { WateringHistory } from '../../watering/model/WateringHistory.entity';

@Controller('/plants')
export class PlantController {
  constructor(private plantService: PlantService) {}

  @Get()
  async getPlants(): Promise<PlantResponseDto[]> {
    return plainToInstance(PlantResponseDto, await this.plantService.getAll(1));
  }

  @Get(':id')
  async getPlant(@Param('id') id: number): Promise<PlantResponseDto> {
    return plainToInstance(
      PlantResponseDto,
      await this.plantService.getById(1, Number(id)),
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
