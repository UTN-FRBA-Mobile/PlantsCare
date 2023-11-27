import { Exclude, Expose, Type } from "class-transformer";
import { IsObject, IsString, MaxLength, ValidateNested } from "class-validator";
import { PlantPropertiesDto } from "./PlantProperties.dto";

@Exclude()
export class CreatePlantDto {
  @Expose()
  @IsString()
  @MaxLength(64)
  name: string;

  @Expose()
  @Type(() => PlantPropertiesDto)
  @ValidateNested()
  properties: PlantPropertiesDto;
}
