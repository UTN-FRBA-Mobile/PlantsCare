import { Module } from '@nestjs/common';
import { UserService } from './providers/User.service';
import { UserController } from './controllers/User.controller';
import { TypeOrmModule } from '@nestjs/typeorm';
import { User } from './model/User.entity';

@Module({
  imports: [TypeOrmModule.forFeature([User])],
  controllers: [UserController],
  providers: [UserService],
})
export class UserModule {}
