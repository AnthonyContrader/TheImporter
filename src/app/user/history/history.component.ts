import { Component, OnInit } from '@angular/core';
import { HistoryDTO } from 'src/dto/historyDTO';
import { LongDTO } from 'src/dto/longdto';
import { HistoryService } from 'src/service/history.service';
import { UserService } from 'src/service/user.service';
import { UserDTO } from 'src/dto/userdto';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
	
	listHistory: HistoryDTO[] =[];
	listUserID: LongDTO = new LongDTO();
	UserList:UserDTO[]=[];
	

  constructor(private service: HistoryService,private userservice: UserService) { }

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
				//this.userservice.read(this.listUserID.listid[i]).subscribe(user =>{
				//	this.UserList.push(user);
				//});
			}
		}
		
		console.log("stampo lista utenti ID: "+this.listUserID.listid);
	}

}
