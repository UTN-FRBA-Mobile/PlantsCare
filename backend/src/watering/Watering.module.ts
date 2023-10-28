import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { WateringHistory } from './model/WateringHistory.entity';
import { WateringController } from './controllers/Watering.controller';
import { WateringService } from './providers/Watering.service';

@Module({
  imports: [TypeOrmModule.forFeature([WateringHistory])],
  controllers: [WateringController],
  providers: [WateringService],
})
export class WateringModule {}
