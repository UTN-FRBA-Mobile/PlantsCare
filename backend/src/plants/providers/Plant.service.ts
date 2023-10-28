import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Plant } from '../model/Plant.entity';
import { entityNotFoundError } from '../../utils/entities/entityNotFoundError';
import { CreatePlantDto } from '../dtos/CreatePlant.dto';
import { PlantFactory } from './Plant.factory';
import { entityWasDeleted } from '../../utils/entities/entityWasDeleted';

@Injectable()
export class PlantService {
  constructor(
    @InjectRepository(Plant)
    private plantsRepository: Repository<Plant>,
    private plantFactory: PlantFactory,
  ) {}
  getAll(userId: number): Promise<Plant[]> {
    return this.plantsRepository.find();
  }
  async getById(userId: number, id: number): Promise<Plant> {
    return this.plantsRepository
      .findOne({ where: { id }, relations: ['history', 'wateringFrequency'] })
      .then((plant) => plant || entityNotFoundError(Plant.name, id));
  }

  async create(userId: number, plantDto: CreatePlantDto): Promise<Plant> {
    return this.plantsRepository.save(
      await this.plantFactory.createPlant(plantDto),
    );
  }

  async delete(userId: number, id: number): Promise<any> {
    return this.plantsRepository
      .delete(id)
      .then(entityWasDeleted(Plant.name, id))
      .then(() => ({ status: 'deleted' }));
  }
}
