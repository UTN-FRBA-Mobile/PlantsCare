import { Exclude, Expose } from 'class-transformer';
import { IsDateString, IsOptional } from 'class-validator';

@Exclude()
export class PlantWateringDto {
  @Expose()
  @IsOptional()
  @IsDateString()
  date: string;
}
