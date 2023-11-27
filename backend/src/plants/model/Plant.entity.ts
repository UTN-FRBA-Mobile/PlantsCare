import { PersistentObject } from '../../utils/entities/PersistentObject';
import { Column, Entity, OneToMany, OneToOne, JoinColumn } from 'typeorm';
import { WateringFrequency } from './WateringFrequency.entity';
import { WateringHistory } from '../../watering/model/WateringHistory.entity';
import { PlantProperties } from './properties/PlantProperties';

@Entity()
export class Plant extends PersistentObject {
  @Column()
  name: string;

  @Column()
  type: string;

  @Column({ type: 'varchar', length: 1024 })
  description: string;

  @Column()
  currentWateringFrequency: number;

  @Column(() => PlantProperties)
  properties: PlantProperties;

  @Column('simple-array')
  imageGallery: string[];

  @OneToOne(() => WateringFrequency, { cascade: true })
  @JoinColumn()
  wateringFrequency: WateringFrequency;

  @OneToMany(() => WateringHistory, (history) => history.plant, {
    cascade: true,
  })
  history: WateringHistory[];

  constructor(
    name: string,
    type: string,
    description: string,
    currentWateringFrequency: number,
    imageGallery: string[],
    wateringFrequency: WateringFrequency,
    history: WateringHistory[],
  ) {
    super();
    this.name = name;
    this.type = type;
    this.description = description;
    this.imageGallery = imageGallery;
    this.currentWateringFrequency = currentWateringFrequency;
    this.wateringFrequency = wateringFrequency;
    this.history = history;
  }

  changeCurrentWateringFrequency(currentWateringFrequency: number): void {
    this.currentWateringFrequency = currentWateringFrequency;
  }

  sortHistory() {
    this.history = this.history.sort(
      (history, otherHistory) =>
        history.date.getTime() - otherHistory.date.getTime(),
    );
    return this;
  }
}
