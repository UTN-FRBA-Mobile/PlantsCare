import { Exclude, Expose, Type } from 'class-transformer';
import { IsDate, ValidateNested } from 'class-validator';
import { PlantResponseDto } from '../../plants/dtos/PlantResponse.dto';
import { MinimalPlantResponseDto } from '../../plants/dtos/MinimalPlantResponse.dto';

@Exclude()
export class WateringByDateDto {
  @Expose()
  @IsDate()
  date: Date;

  @Expose()
  @Type(() => MinimalPlantResponseDto)
  @ValidateNested({ each: true })
  plants: MinimalPlantResponseDto[];
}
