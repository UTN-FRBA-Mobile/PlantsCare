import { Injectable } from '@nestjs/common';
import { CreatePlantDto } from '../dtos/CreatePlant.dto';
import { Plant } from '../model/Plant.entity';
import { WateringFrequency } from '../model/WateringFrequency.entity';
import { WateringHistory } from '../../watering/model/WateringHistory.entity';
import { addDays, getDay, isEqual, startOfDay } from 'date-fns';
import { WateringHistoryStatus } from '../../watering/model/WateringHistoryStatus';

@Injectable()
export class PlantFactory {
  async createPlant(path: string): Promise<Plant> {
    const plantData = this.generatePlantData();
    return new Plant(
      plantData.type,
      plantData.type,
      plantData.description,
      plantData.wateringFrequency.november,
      [this.createImageUrl(path)],
      plantData.wateringFrequency,
      plantData.history,
    );
  }

  private createImageUrl(path: string) {
    return `${process.env.STATIC_CONTENT_URL}/${path}`;
  }

  private generatePlantData() {
    // magic magic
    const { type, image, description } = this.getNameAndDescription();
    const wateringFrequency = this.calculateWateringFrequency();
    const history = this.createFirstYearOfHistory(wateringFrequency);
    return { type, image, description, wateringFrequency, history };
  }

  calculateWateringFrequency(): WateringFrequency {
    // black magic
    const factor = Math.max(Math.ceil(Math.random()), 4);
    const summer = Math.max(factor - 4, 1);
    const autumn = Math.max(factor - 2, 2);
    const winter = Math.max(factor - 1, 4);
    const spring = Math.max(factor - 3, 3);
    return new WateringFrequency(
      2,
      2,
      2,
      autumn,
      autumn,
      winter,
      winter,
      winter,
      spring,
      spring,
      spring,
      2,
    );
  }

  createFirstYearOfHistory(frequency: WateringFrequency): WateringHistory[] {
    const current = startOfDay(new Date());
    return this.generate365Days(current).reduce(
      this.#createHistory(frequency),
      {
        current,
        history: [],
      },
    ).history;
  }

  #createHistory =
    (frequency: WateringFrequency) =>
    (watering: { current: Date; history: WateringHistory[] }, date: Date) => ({
      current: this.getNextWatering(date, watering.current, frequency),
      history: watering.history.concat(
        new WateringHistory(date, this.wateringStatus(date, watering.current)),
      ),
    });

  private getNextWatering(
    date: Date,
    currentWatering: Date,
    frequency: WateringFrequency,
  ) {
    return isEqual(date, currentWatering)
      ? addDays(currentWatering, frequency.getFrequencyByMonth(date))
      : currentWatering;
  }

  private wateringStatus(date: Date, nextWatering: Date) {
    return isEqual(date, nextWatering)
      ? WateringHistoryStatus.NEEDS_WATERING
      : WateringHistoryStatus.NO_INFO;
  }
  private generate365Days(today: Date) {
    return Array.from({ length: 365 }, (_, index) => {
      return addDays(today, index);
    });
  }

  private getNameAndDescription() {
    return this.getPlantsData()[4];
    // Math.floor(Math.random() * this.getPlantsData().length)
  }

  private getPlantsData() {
    return [
      {
        type: 'Mint',
        image: 'https://i.imgur.com/pd5hWzH.jpeg',
        description:
          'Mints have square stems and opposite aromatic leaves. Many can spread vegetatively by stolons and can be aggressive in gardens. The small flowers are usually pale purple, pink, or white in colour and are arranged in clusters, either forming whorls or crowded together in a terminal spike',
      },
      {
        type: 'Rose',
        image: 'https://i.imgur.com/EbeawTE.jpeg',
        description:
          'Roses are erect, climbing, or trailing shrubs, the stems of which are usually copiously armed with prickles of various shapes and sizes, commonly called thorns. The leaves are alternate and pinnately compound (i.e., feather-formed), usually with oval leaflets that are sharply toothed. The flowers of wild roses usually have five petals, whereas the flowers of cultivated roses are often double (i.e., with multiple sets of petals)',
      },
      {
        type: 'Golden Pothos',
        image: 'https://i.imgur.com/JWyrHZb.jpeg',
        description:
          'The golden pothos (Epipremnum aureum) is a popular houseplant that is commonly seen in Australia, Asia, and the West Indies. It goes by many nicknames, including "devil\'s ivy", because it is so hard to kill and can even grow in low light conditions. Golden pothos has poisonous sap, so it should be kept away from pets and children. ',
      },
      {
        type: 'Snake plant',
        image: 'https://i.imgur.com/Qz7pPJ8.jpeg',
        description:
          'Snake plant can be considered a houseplant and an architectural display due to its sword-like leaves with bold striping patterns, which are distinctive and eye-catching. However, use caution with this plant because it is poisonous when ingested and can cause nausea, vomiting, and even swelling of the throat and tongue. ',
      },
      {
        type: 'Albahaca',
        image: 'https://i.imgur.com/FioQT6e.jpeg',
        description:
          'La albahaca (Ocimum basilicum) is an annual, or sometimes perennial, herb used for its leaves. Depending on the variety, plants can reach heights of between 30 and 150 cm (1 and 5 ft). Its leaves are richly green and ovate, but otherwise come in a wide variety of sizes and shapes depending on cultivar. Leaf sizes range from 3 to 11 cm (1 to 4+1⁄2 in) long, and between 1 and 6 cm (1⁄2 and 2+1⁄2 in) wide',
      },
    ];
  }
}
