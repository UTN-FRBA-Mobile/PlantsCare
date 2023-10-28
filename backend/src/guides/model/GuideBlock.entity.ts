import { Column, Entity, ManyToOne } from 'typeorm';
import { PersistentObject } from '../../utils/entities/PersistentObject';
import { Guide } from './Guide.entity';
import { GuideType } from './GuideType';

@Entity()
export class GuideBlock extends PersistentObject {
  @Column()
  type: GuideType;

  @Column({ type: 'varchar', length: 2048 })
  value: string;

  @ManyToOne(() => Guide, (guide) => guide.blocks, {
    onDelete: 'CASCADE',
  })
  guide: Guide;

  constructor(type: GuideType, value: string) {
    super();
    this.type = type;
    this.value = value;
  }
}
