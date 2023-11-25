import { Exclude, Expose, Type } from 'class-transformer';
import { IsNotEmptyObject, IsString, ValidateNested } from 'class-validator';
import { PlantPropertiesDto } from './PlantProperties.dto';

@Exclude()
export class PlantMetadataDto {
  @Expose()
  @IsString()
  name: string;

  @Expose()
  @IsNotEmptyObject()
  @Type(() => PlantPropertiesDto)
  @ValidateNested()
  properties: PlantPropertiesDto;
}
