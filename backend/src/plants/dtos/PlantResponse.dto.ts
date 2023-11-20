import { Exclude, Expose, Type } from 'class-transformer';
import { IsString, ValidateNested } from 'class-validator';
import { WateringFrequencyDto } from './WateringFrequency.dto';
import { WateringHistoryDto } from './WateringHistory.dto';
import { MinimalPlantResponseDto } from './MinimalPlantResponse.dto';

@Exclude()
export class PlantResponseDto extends MinimalPlantResponseDto {
  @Expose()
  @Type(() => WateringFrequencyDto)
  @ValidateNested()
  wateringFrequency: WateringFrequencyDto;

  @Expose()
  @Type(() => WateringHistoryDto)
  @ValidateNested({ each: true })
  history: WateringHistoryDto[];

  @Expose()
  @IsString()
  nextWateringInDays: number;
}
