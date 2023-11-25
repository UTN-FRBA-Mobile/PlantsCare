import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { PlantModule } from './plants/Plant.module';
import { WateringModule } from './watering/Watering.module';
import { UserModule } from './users/User.module';
import { GuideModule } from './guides/Guide.module';
import { ScheduleModule } from '@nestjs/schedule';
import { ImageModule } from './images/Image.module';

const TypeORM = TypeOrmModule.forRoot({
  type: 'mysql',
  url: process.env.DB_URL,
  synchronize: true,
  entities: ['dist/**/*.entity{.ts,.js}'],
});
@Module({
  imports: [
    ScheduleModule.forRoot(),
    TypeORM,
    PlantModule,
    WateringModule,
    UserModule,
    GuideModule,
    ImageModule,
  ],
  controllers: [],
  providers: [],
})
export class AppModule {}
