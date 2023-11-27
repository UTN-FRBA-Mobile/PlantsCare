import { Column } from 'typeorm';
import { SunExposure } from './SunExposure';
import { Difficulty } from './Difficulty';
import { Environment } from './Environment';
import { PlantSize } from './PlantSize';

export class PlantProperties {
  @Column({ type: 'enum', enum: PlantSize })
  size: PlantSize;

  @Column({ type: 'enum', enum: Environment })
  environment: Environment;

  @Column({ type: 'enum', enum: SunExposure })
  sunExposure: SunExposure;

  @Column({ type: 'enum', enum: Difficulty })
  difficulty: Difficulty;
}
