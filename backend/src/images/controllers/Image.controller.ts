import {
  BadRequestException,
  Controller,
  Get,
  Param,
  Res,
} from '@nestjs/common';
import { createReadStream } from 'fs';
import { Response } from 'express';
import { isAlphanumeric } from 'class-validator';
import { rethrow } from '@nestjs/core/helpers/rethrow';

@Controller('images')
export class ImageController {
  @Get('/plants/:id')
  getPlantImage(@Param('id') id: string, @Res() res: Response) {
    isAlphanumeric(id) || rethrow(new BadRequestException('invalid image id'));
    const file = createReadStream(`./images/plants/${id}`);
    res.setHeader('Content-Type', 'image/jpeg');
    file.pipe(res);
  }
}
