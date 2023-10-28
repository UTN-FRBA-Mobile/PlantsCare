import { Exclude, Expose } from "class-transformer";

@Exclude()
export class IdDto {
  @Expose()
  id: number;
}
