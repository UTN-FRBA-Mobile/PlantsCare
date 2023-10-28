import { Column } from 'typeorm';
import { TemperatureFormat } from './TemperatureFormat';

export class UserSettings {
  @Column({ type: 'enum', enum: TemperatureFormat })
  temperatureFormat: TemperatureFormat;

  @Column()
  location: string;
}
