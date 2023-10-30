import { Exclude, Expose } from 'class-transformer';
import { IntersectionType } from '@nestjs/mapped-types';
import { IdDto } from '../../plants/dtos/Id.dto';
import { CreateGuideDto } from './CreateGuide.dto';
import { IsDate } from 'class-validator';

@Exclude()
export class GuideResponseDto extends IntersectionType(IdDto, CreateGuideDto) {
  @Expose()
  @IsDate()
  createdAt: Date;
}
