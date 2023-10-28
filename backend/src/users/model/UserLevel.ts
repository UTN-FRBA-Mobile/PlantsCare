import { Column } from 'typeorm';

export class UserLevel {
  @Column()
  value: number;

  @Column()
  experience: number;
}
