import { Exclude, Expose, Type } from 'class-transformer';
import { IdDto } from './Id.dto';
import { IsInt, IsString, ValidateNested } from 'class-validator';
import { PlantPropertiesDto } from './PlantProperties.dto';

@Exclude()
export class MinimalPlantResponseDto extends IdDto {
  @Expose()
  @IsString()
  name: string;

  @Expose()
  @IsString()
  type: string;

  @Expose()
  @IsString()
  description: string;

  @Expose()
  @IsInt()
  currentWateringFrequency: number;

  @Expose()
  @Type(() => PlantPropertiesDto)
  @ValidateNested()
  properties: PlantPropertiesDto;

  @Expose()
  @IsString({ each: true })
  imageGallery: string[];
}
