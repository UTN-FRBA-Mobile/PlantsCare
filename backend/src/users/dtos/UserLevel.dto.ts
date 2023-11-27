import { Exclude, Expose } from "class-transformer";
import { IsInt } from "class-validator";

@Exclude()
export class UserLevelDto {
  @Expose()
  @IsInt()
  level: number;

  @Expose()
  @IsInt()
  experience: number;
}
