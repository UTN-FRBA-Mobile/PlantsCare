import { Exclude, Expose } from 'class-transformer';
import { IsEnum, IsString } from 'class-validator';
import { GuideType } from '../model/GuideType';

@Exclude()
export class GuideBlockDto {
  @Expose()
  @IsEnum(GuideType)
  type: GuideType;

  @Expose()
  @IsString()
  value: string;
}
