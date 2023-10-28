import { entityNotFoundError } from './entityNotFoundError';
import { DeleteResult } from 'typeorm';

export const entityWasDeleted =
  (entity: string, id: number) =>
  ({ affected }: DeleteResult) =>
    (affected > 0 && { status: 'deleted' }) || entityNotFoundError(entity, id);
