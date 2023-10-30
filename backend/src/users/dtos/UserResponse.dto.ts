import { IdDto } from '../../plants/dtos/Id.dto';
import { Exclude, Expose, Type } from 'class-transformer';
import { IsInt, IsString, IsUrl, ValidateNested } from 'class-validator';
import { UserLevelDto } from './UserLevel.dto';
import { UserSettingsDto } from './UserSettings.dto';

@Exclude()
export class UserResponseDto extends IdDto {
  @Expose()
  @IsString()
  name: string;

  @Expose()
  @IsString()
  email: string;

  @Expose()
  @IsUrl()
  image: string;

  @Expose()
  @Type(() => UserLevelDto)
  @ValidateNested()
  level: UserLevelDto;

  @Expose()
  @Type(() => UserSettingsDto)
  @ValidateNested()
  settings: UserSettingsDto;

  @Expose()
  @IsInt()
  plants: number;
}
