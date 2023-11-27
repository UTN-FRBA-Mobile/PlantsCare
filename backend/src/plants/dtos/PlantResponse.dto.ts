import { Exclude, Expose, Type } from 'class-transformer';
import { IsEnum, IsString, ValidateNested } from 'class-validator';
import { WateringFrequencyDto } from './WateringFrequency.dto';
import { WateringHistoryDto } from './WateringHistory.dto';
import { MinimalPlantResponseDto } from './MinimalPlantResponse.dto';
import { WateringHistoryStatus } from '../../watering/model/WateringHistoryStatus';

@Exclude()
export class PlantResponseDto extends MinimalPlantResponseDto {
  @Expose()
  @Type(() => WateringFrequencyDto)
  @ValidateNested()
  wateringFrequency: WateringFrequencyDto;

  @Expose()
  @IsEnum(WateringHistoryStatus)
  status: WateringHistoryStatus;

  @Expose()
  @Type(() => WateringHistoryDto)
  @ValidateNested({ each: true })
  history: WateringHistoryDto[];

  @Expose()
  @IsString()
  nextWateringInDays: number;
}
