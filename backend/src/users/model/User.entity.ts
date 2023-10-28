import { PersistentObject } from '../../utils/entities/PersistentObject';
import { Column, Entity } from 'typeorm';
import { UserLevel } from './UserLevel';
import { UserSettings } from './UserSettings';

@Entity()
export class User extends PersistentObject {
  @Column()
  name: string;

  @Column()
  email: string;

  @Column()
  image: string;

  @Column(() => UserLevel)
  level: UserLevel;

  @Column(() => UserSettings)
  settings: UserSettings;

  constructor(
    name: string,
    email: string,
    image: string,
    level: UserLevel,
    settings: UserSettings,
  ) {
    super();
    this.name = name;
    this.email = email;
    this.image = image;
    this.level = level;
    this.settings = settings;
  }
}
