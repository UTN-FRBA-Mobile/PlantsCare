import { Exclude, Expose } from "class-transformer";
import { IsInt, IsPositive } from "class-validator";

@Exclude()
export class WateringFrequencyDto {
  @Expose()
  @IsInt()
  january: number;
  @Expose()
  @IsInt()
  february: number;
  @Expose()
  @IsInt()
  march: number;
  @Expose()
  @IsInt()
  april: number;
  @Expose()
  @IsInt()
  may: number;
  @Expose()
  @IsInt()
  june: number;
  @Expose()
  @IsInt()
  july: number;
  @Expose()
  @IsInt()
  august: number;
  @Expose()
  @IsInt()
  september: number;
  @Expose()
  @IsInt()
  october: number;
  @Expose()
  @IsInt()
  november: number;
  @Expose()
  @IsInt()
  december: number;
}
