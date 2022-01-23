export class RequsetParams {
    constructor(
        public order_by:string|'dragon_id',
        public size:number|5,
        public filter_by:string[]|null,
        public page:number|0
    ) {}
}