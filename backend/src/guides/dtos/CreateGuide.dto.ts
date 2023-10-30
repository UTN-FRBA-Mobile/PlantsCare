import { Exclude, Expose, Type } from 'class-transformer';
import {
  IsArray,
  IsInt,
  IsPositive,
  IsString,
  ValidateNested,
} from 'class-validator';
import { GuideBlockDto } from './GuideBlock.dto';

@Exclude()
export class CreateGuideDto {
  @Expose()
  @IsString()
  name: string;

  @Expose()
  @IsPositive()
  @IsInt()
  readingTimeInMinutes: number;

  @Expose()
  @IsString()
  author: string;

  @Expose()
  @IsString()
  title: string;

  @Expose()
  @IsArray()
  @Type(() => GuideBlockDto)
  @ValidateNested({ each: true })
  blocks: GuideBlockDto[];
}
