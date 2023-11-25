import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Between, Repository } from 'typeorm';
import { Plant } from '../model/Plant.entity';
import { entityNotFoundError } from '../../utils/entities/entityNotFoundError';
import { CreatePlantDto } from '../dtos/CreatePlant.dto';
import { PlantFactory } from './Plant.factory';
import { entityWasDeleted } from '../../utils/entities/entityWasDeleted';
import { addWeeks, startOfToday, startOfWeek } from 'date-fns';
import { startOfWeekWithOptions } from 'date-fns/fp';
import { PlantPropertiesDto } from '../dtos/PlantProperties.dto';
import { PlantMetadataDto } from '../dtos/PlantMetadata.dto';

@Injectable()
export class PlantService {
  constructor(
    @InjectRepository(Plant)
    private plantsRepository: Repository<Plant>,
    private plantFactory: PlantFactory,
  ) {}
  getAll(userId: number): Promise<Plant[]> {
    return this.plantsRepository.find({
      where: {
        history: {
          date: Between(startOfToday(), addWeeks(startOfToday(), 1)),
        },
      },
      relations: ['history'],
    });
  }
  async getById(userId: number, id: number): Promise<Plant> {
    return this.plantsRepository
      .findOne({
        where: { id },
        relations: ['history', 'wateringFrequency'],
      })
      .then((plant) => plant || entityNotFoundError(Plant.name, id));
  }

  async create(userId: number, path: string): Promise<Plant> {
    return this.plantsRepository.save(
      await this.plantFactory.createPlant(path),
    );
  }

  async delete(userId: number, id: number): Promise<any> {
    return this.plantsRepository
      .delete(id)
      .then(entityWasDeleted(Plant.name, id))
      .then(() => ({ status: 'deleted' }));
  }

  async setProperties(
    userId: number,
    id: number,
    { properties, name }: PlantMetadataDto,
  ): Promise<Plant> {
    await this.plantsRepository.update(id, { name, properties });
    return this.getById(userId, id);
  }
}
