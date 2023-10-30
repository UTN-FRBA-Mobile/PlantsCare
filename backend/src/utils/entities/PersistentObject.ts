import { PrimaryGeneratedColumn } from "typeorm";

export class PersistentObject {

  @PrimaryGeneratedColumn()
  id: number;

  public hasSameId(id: number): boolean {
    return this.id === id;
  }

  public isEquals(persistObject: this): boolean {
    return this.hasSameId(persistObject.id);
  }

  public getId(): number {
    return this.id;
  }

  public setId(id: number) {
    this.id = id;
    return this;
  }

}
