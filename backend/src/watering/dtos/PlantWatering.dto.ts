import { Exclude, Expose } from 'class-transformer';
import { IsDateString } from 'class-validator';

@Exclude()
export class PlantWateringDto {
  @Expose()
  @IsDateString()
  date: string;
}
