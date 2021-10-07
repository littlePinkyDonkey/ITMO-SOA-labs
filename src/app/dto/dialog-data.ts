import { Person } from '../dto/person';
import { Coordinates } from '../dto/coordinates';

export class DialogData {
    name: string|undefined;
    coordinates: Coordinates|undefined;
    creationDate: Date|null = null;
    public age: number|undefined;
    color: string|undefined;
    type: string|undefined;
    character: string|undefined;
    killer: Person|null = null;

    constructor() {}
}