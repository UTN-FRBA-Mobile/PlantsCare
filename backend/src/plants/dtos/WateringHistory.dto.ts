import { Exclude, Expose } from 'class-transformer';
import { IsDate, IsEnum } from 'class-validator';
import { WateringHistoryStatus } from '../../watering/model/WateringHistoryStatus';

@Exclude()
export class WateringHistoryDto {
  @Expose()
  @IsDate()
  date: Date;

  @Expose()
  @IsEnum(WateringHistoryStatus)
  status: WateringHistoryStatus;
}
