export class HistoryDTO {

	id: number;

    productID: number;

    userID: number;

    constructor(userid:number,productid:number){
        this.productID=productid;
        this.userID=userid;
    }
}
