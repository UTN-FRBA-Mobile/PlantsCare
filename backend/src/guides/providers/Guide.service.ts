import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Guide } from '../model/Guide.entity';
import { Repository } from 'typeorm';
import { entityNotFoundError } from '../../utils/entities/entityNotFoundError';
import { entityWasDeleted } from '../../utils/entities/entityWasDeleted';
import { CreateGuideDto } from '../dtos/CreateGuide.dto';
import { GuideBlock } from '../model/GuideBlock.entity';

@Injectable()
export class GuideService {
  constructor(
    @InjectRepository(Guide)
    private guidesRepository: Repository<Guide>,
  ) {}

  getAll(): Promise<Guide[]> {
    return this.guidesRepository.find({ relations: ['blocks'] });
  }

  getById(id: number): Promise<Guide> {
    return this.guidesRepository
      .findOne({ where: { id }, relations: ['blocks'] })
      .then((guide) => guide || entityNotFoundError(Guide.name, id));
  }

  create(guideDto: CreateGuideDto): Promise<Guide> {
    return this.guidesRepository.save(
      new Guide(
        guideDto.name,
        guideDto.readingTimeInMinutes,
        guideDto.author,
        guideDto.title,
        guideDto.blocks.map(({ type, value }) => new GuideBlock(type, value)),
      ),
    );
  }

  delete(id: number) {
    return this.guidesRepository
      .delete(id)
      .then(entityWasDeleted(Guide.name, id))
      .then(() => ({ status: 'deleted' }));
  }
}
