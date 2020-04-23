export class HistoryDTO {

	id: number;

    productId: number;

    userId: number;

    constructor(userid:number,productid:number){
        this.productId=productid;
        this.userId=userid;
    }
}
