import { BadRequestException, NotFoundException } from '@nestjs/common';

export class EntityNotFoundError extends NotFoundException {
  constructor(entity: string, id: number) {
    super(`Could not find any entity of type ${entity} with id ${id}`);
  }
}

export const entityNotFoundError = (entity: string, id: number): never => {
  throw new EntityNotFoundError(entity, id);
};
