import { Exclude, Expose } from 'class-transformer';
import { IsEnum } from 'class-validator';
import { Environment } from '../model/properties/Environment';
import { SunExposure } from '../model/properties/SunExposure';
import { Difficulty } from '../model/properties/Difficulty';
import { PlantSize } from '../model/properties/PlantSize';

@Exclude()
export class PlantPropertiesDto {
  @Expose()
  @IsEnum(PlantSize)
  size: PlantSize;

  @Expose()
  @IsEnum(Environment)
  environment: Environment;

  @Expose()
  @IsEnum(SunExposure)
  sunExposure: SunExposure;

  @Expose()
  @IsEnum(Difficulty)
  difficulty: Difficulty;
}
