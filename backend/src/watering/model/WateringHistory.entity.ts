import { Column, Entity, ManyToOne } from 'typeorm';
import { PersistentObject } from '../../utils/entities/PersistentObject';
import { WateringHistoryStatus } from './WateringHistoryStatus';
import { Plant } from '../../plants/model/Plant.entity';

@Entity()
export class WateringHistory extends PersistentObject {
  @Column()
  date: Date;

  @Column({ type: 'enum', enum: WateringHistoryStatus })
  status: WateringHistoryStatus;

  @ManyToOne(() => Plant, (plant) => plant.history, { onDelete: 'CASCADE' })
  plant: Plant;

  constructor(
    date: Date,
    status: WateringHistoryStatus = WateringHistoryStatus.NO_INFO,
  ) {
    super();
    this.date = date;
    this.status = status;
  }

  changeStatus(status: WateringHistoryStatus) {
    this.status = status;
  }
}
