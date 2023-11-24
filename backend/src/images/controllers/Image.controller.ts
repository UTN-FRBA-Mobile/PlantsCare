import { Controller, Get, Param, Res } from '@nestjs/common';
import { createReadStream } from 'fs';
import { Response } from 'express';

@Controller('images')
export class ImageController {
  @Get('/plants/:id')
  getPlantImage(@Param('id') id: string, @Res() res: Response) {
    const file = createReadStream(`./images/plants/${id}`);
    file.pipe(res);
  }
}
