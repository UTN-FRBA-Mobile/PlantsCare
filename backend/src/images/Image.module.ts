import { Module } from '@nestjs/common';
import { ImageController } from './controllers/Image.controller';

@Module({
  controllers: [ImageController],
})
export class ImageModule {}
