import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Guide } from './model/Guide.entity';
import { GuideService } from './providers/Guide.service';
import { GuideController } from './controllers/Guide.controller';

@Module({
  imports: [TypeOrmModule.forFeature([Guide])],
  providers: [GuideService],
  controllers: [GuideController],
})
export class GuideModule {}
