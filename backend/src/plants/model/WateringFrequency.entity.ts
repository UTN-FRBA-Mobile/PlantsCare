import { Column, Entity } from 'typeorm';
import { PersistentObject } from '../../utils/entities/PersistentObject';
import { getMonth } from "date-fns";

@Entity()
export class WateringFrequency extends PersistentObject {
  @Column({ type: 'integer' })
  january: number;
  @Column({ type: 'integer' })
  february: number;
  @Column({ type: 'integer' })
  march: number;
  @Column({ type: 'integer' })
  april: number;
  @Column({ type: 'integer' })
  may: number;
  @Column({ type: 'integer' })
  june: number;
  @Column({ type: 'integer' })
  july: number;
  @Column({ type: 'integer' })
  august: number;
  @Column({ type: 'integer' })
  september: number;
  @Column({ type: 'integer' })
  october: number;
  @Column({ type: 'integer' })
  november: number;
  @Column({ type: 'integer' })
  december: number;

  constructor(
    january: number,
    february: number,
    march: number,
    april: number,
    may: number,
    june: number,
    july: number,
    august: number,
    september: number,
    october: number,
    november: number,
    december: number,
  ) {
    super();
    this.january = january;
    this.february = february;
    this.march = march;
    this.april = april;
    this.may = may;
    this.june = june;
    this.july = july;
    this.august = august;
    this.september = september;
    this.october = october;
    this.november = november;
    this.december = december;
  }

  getAllSorted(): number[] {
    return [
      this.january,
      this.february,
      this.march,
      this.april,
      this.may,
      this.june,
      this.july,
      this.august,
      this.september,
      this.october,
      this.november,
      this.december,
    ];
  }

  getFrequencyByMonth(date: Date): number {
    return this.getAllSorted()[getMonth(date)];
  }
}
