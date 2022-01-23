export class Person {
    constructor(
        public personId:number|null, 
        public name:string, 
        public passportId:string|null, 
        public eyeColor:string, 
        public hairColor:string
    ) {}
}