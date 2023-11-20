import { Injectable } from '@nestjs/common';
import { User } from '../model/User.entity';
import { TemperatureFormat } from '../model/TemperatureFormat';

@Injectable()
export class UserService {
  async getMe(id: number): Promise<User> {
    return new User(
      'John Doe',
      'johndoe@example.com',
      'https://i.imgur.com/gPi0UJx.jpeg',
      {
        value: 12,
        experience: 55,
      },
      {
        temperatureFormat: TemperatureFormat.CELSIUS,
        location: 'Buenos Aires, Caballito',
      },
    );
  }
}
