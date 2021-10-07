import { Person } from "./person";
import { Coordinates } from "./coordinates";

export class Dragon {
    constructor(
        public id:number,
        public name:string, 
        public coordinates:Coordinates, 
        public creationDate:Date, 
        public age:number|null, 
        public color:string|null, 
        public type:string, 
        public character:string, 
        public killer:Person|null
    ) {}
}