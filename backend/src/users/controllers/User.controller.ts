import { Controller, Get } from '@nestjs/common';
import { UserResponseDto } from '../dtos/UserResponse.dto';
import { plainToInstance } from 'class-transformer';
import { UserService } from '../providers/User.service';

@Controller('users')
export class UserController {
  constructor(private userService: UserService) {}

  @Get('me')
  async getMe(): Promise<UserResponseDto> {
    return plainToInstance(UserResponseDto, await this.userService.getMe(1));
  }
}
