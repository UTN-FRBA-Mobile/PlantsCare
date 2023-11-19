import { Exclude, Expose } from 'class-transformer';
import { ApiProperty } from '@nestjs/swagger';
import { WateringHistoryStatus } from '../model/WateringHistoryStatus';
import { IsDate } from 'class-validator';

@Exclude()
export class PlantWateringStatusDto {
  @Expose()
  @ApiProperty({ example: WateringHistoryStatus.WATERED })
  status: WateringHistoryStatus;

  @Expose()
  @IsDate()
  date: Date;
}
