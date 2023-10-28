import { Column, CreateDateColumn, Entity, OneToMany } from 'typeorm';
import { PersistentObject } from '../../utils/entities/PersistentObject';
import { GuideBlock } from './GuideBlock.entity';

@Entity()
export class Guide extends PersistentObject {
  @Column()
  name: string;

  @Column()
  readingTimeInMinutes: number;

  @Column()
  author: string;

  @Column()
  title: string;

  @OneToMany(() => GuideBlock, (block) => block.guide, { cascade: true })
  blocks: GuideBlock[];

  @CreateDateColumn()
  createdAt: Date;
  constructor(
    name: string,
    readingTimeInMinutes: number,
    author: string,
    title: string,
    blocks: GuideBlock[],
  ) {
    super();
    this.name = name;
    this.readingTimeInMinutes = readingTimeInMinutes;
    this.author = author;
    this.title = title;
    this.blocks = blocks;
  }
}
