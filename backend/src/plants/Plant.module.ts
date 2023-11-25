import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { PlantController } from './controllers/Plant.controller';
import { PlantService } from './providers/Plant.service';
import { Plant } from './model/Plant.entity';
import { PlantFactory } from './providers/Plant.factory';
import { MulterModule } from '@nestjs/platform-express';

@Module({
  imports: [
    TypeOrmModule.forFeature([Plant]),
    MulterModule.register({ dest: './images/plants' }),
  ],
  providers: [PlantService, PlantFactory],
  controllers: [PlantController],
})
export class PlantModule {}
