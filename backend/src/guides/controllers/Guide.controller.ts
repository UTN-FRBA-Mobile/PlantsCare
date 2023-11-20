import { Body, Controller, Delete, Get, Param, Post } from '@nestjs/common';
import { plainToInstance } from 'class-transformer';
import { GuideService } from '../providers/Guide.service';
import { GuideResponseDto } from '../dtos/GuideResponse.dto';
import { CreateGuideDto } from '../dtos/CreateGuide.dto';

@Controller('/guides')
export class GuideController {
  constructor(private guideService: GuideService) {}

  @Get()
  async getAll(): Promise<GuideResponseDto[]> {
    return plainToInstance(GuideResponseDto, await this.guideService.getAll());
  }

  @Get(':id')
  async getBy(@Param('id') id: number): Promise<GuideResponseDto> {
    return plainToInstance(
      GuideResponseDto,
      await this.guideService.getById(id),
    );
  }

  @Post()
  async create(@Body() guideDto: CreateGuideDto): Promise<GuideResponseDto> {
    return plainToInstance(
      GuideResponseDto,
      await this.guideService.create(guideDto),
    );
  }

  @Delete(':id')
  async delete(@Param('id') id: number): Promise<GuideResponseDto> {
    return plainToInstance(
      GuideResponseDto,
      await this.guideService.delete(id),
    );
  }
}
