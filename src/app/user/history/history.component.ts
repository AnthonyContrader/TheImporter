import { Component, OnInit } from '@angular/core';
import { HistoryDTO } from 'src/dto/historyDTO';
import { LongDTO } from 'src/dto/longdto';
import { HistoryService } from 'src/service/history.service';
import { UserService } from 'src/service/user.service';
import { UserDTO } from 'src/dto/userdto';
import { ProductDTO } from 'src/dto/productdto';
import { ProductService } from 'src/service/product.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
	
	listHistory: HistoryDTO[] =[];
	listUserID: LongDTO = new LongDTO();
	UserList:UserDTO[]=[];
	pressed:boolean;
	public selectedOption: string = "1";
	
	productsID: LongDTO;
	productList: ProductDTO[] = [];

  constructor(private service: HistoryService,private userservice: UserService, private productservice: ProductService) { }

  ngOnInit() {
	this.listUserID.costructor();
	this.retrive();
  }

	retrive(){
		this.service.getAll().subscribe((historyList)=> {
															this.listHistory = historyList;
															this.takeUser(this.listHistory);
															console.log("stampo history completo: ");
															console.log(this.listHistory);													
														})
	}
	
	takeUser(historyList: HistoryDTO[]){
		for (let i = 0; i < historyList.length ; i++){
			if(!this.listUserID.listid.includes(historyList[i].userID)){
				this.listUserID.listid[i]=historyList[i].userID;	
				this.userservice.findbyid(this.listUserID.listid[i]).subscribe(user =>{
					this.UserList.push(user);
				});
			}
		}
		
		console.log("stampo lista utenti ID: "+this.listUserID.listid);
	}

 	press(){
		console.log(parseInt(this.selectedOption));
		this.service.SearchByUser(parseInt(this.selectedOption)).subscribe((products) => {
																						 this.productsID = products;
																						 console.log(this.productsID.listid + "cosa ho ricevuto dal service");
																						 this.takeProducts(this.productsID);
																						 });
	}
	
	takeProducts(products: LongDTO){
		this.productservice.getpages().subscribe((num) => {console.log(num)});
		//this.productsID.listid[0] = 72;
		//this.productsID.listid[1] = 73;
		//console.log("modificato: " + this.productsID.listid);
		this.productservice.getProductsInformation(products).subscribe((list) => {
																					this.productList=list;
																					console.log(this.productList);
																				}) 
	}

}
