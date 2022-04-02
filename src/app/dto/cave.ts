import { Team } from "./team";

export class Cave {
    constructor(
        public caveId:number,
        public teams:Array<Team>
    ) {}
}