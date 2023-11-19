import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { WateringHistory } from './model/WateringHistory.entity';
import { WateringController } from './controllers/Watering.controller';
import { WateringService } from './providers/Watering.service';
import { PlantWateringController } from './controllers/PlantWatering.controller';

@Module({
  imports: [TypeOrmModule.forFeature([WateringHistory])],
  controllers: [WateringController, PlantWateringController],
  providers: [WateringService],
})
export class WateringModule {}
