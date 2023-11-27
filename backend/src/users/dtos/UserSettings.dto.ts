import { Exclude, Expose } from 'class-transformer';
import { IsEnum, IsString } from 'class-validator';
import { TemperatureFormat } from '../model/TemperatureFormat';

@Exclude()
export class UserSettingsDto {
  @Expose()
  @IsEnum(TemperatureFormat)
  temperatureFormat: TemperatureFormat;

  @Expose()
  @IsString()
  location: string;
}
