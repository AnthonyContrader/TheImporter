import { Component, OnInit } from '@angular/core';
import { ExcelService } from 'src/service/excel.service';
import { ExcelDTO } from 'src/dto/exceldto';
import { StringDTO } from 'src/dto/stringdto';
import { ProductDTO } from 'src/dto/productdto';
import { UserDTO } from 'src/dto/userdto';
import { HistoryDTO } from 'src/dto/historyDTO';
import { HistoryService } from 'src/service/history.service';
declare var $: any;

@Component({
  selector: 'app-excel',
  templateUrl: './excel.component.html',
  styleUrls: ['./excel.component.css']
})
export class ExcelComponent implements OnInit {

	excel: ExcelDTO = new ExcelDTO();
  //producttoinsert: ProductDTO = new ProductDTO();
	directory: string;
	preview: StringDTO;
	results: ProductDTO[] = [];
	resultsSize: number = 0;
	
	par: number[] = [];
	par1:number = 0;
	par2:number = 0;
	par3:number = 0;
	par4:number = 0;
	
	//variabili per l'inserimento di history
	user:UserDTO;
	history:HistoryDTO;

  constructor(private service: ExcelService, private historyService: HistoryService) { }

  ngOnInit() {
    
	
	


  }



	check2(){
		
	var limit = 4;
	$('input.single-checkbox').on('change', function(evt) {
   if($(this).siblings(':checked').length >= limit) {
       this.checked = false;
   }
});
	}









    preinsert(directoryLetta: string) {
	console.log(directoryLetta);
    this.service.preinsert(directoryLetta).subscribe((prwSpring) => {
																	this.preview = prwSpring, 
																	console.log(this.preview.titles[0]),
																	console.log(this.preview.line1[0]);
																	})	  	
	}
	
	excelInsert() {
	console.log(this.par1);
	this.par[0] = this.par1;
	this.par[1] = this.par2;
	this.par[2] = this.par3;
	this.par[3] = this.par4;
	console.log(this.par);
    this.service.excelInsert(this.par).subscribe((prwSpring) => {
																	this.results = prwSpring,
																	this.resultsSize = this.results.length; 
																	console.log("ritorno excelInsert: " + this.results)
																	
															      this.user =new UserDTO;
															      this.user=JSON.parse(localStorage.getItem('AUTOKEN'));
															      console.log("id utente:" + this.user.id);
															      console.log("id di un risultato: " + this.results[0].id);
															      
																  for(let i =0; i<this.results.length; i++){
																	  this.history=new HistoryDTO(this.user.id,this.results[i].id);
																      this.history.productID=this.results[i].id;
																      this.history.userID=this.user.id;
																      console.log(this.history.productID);
																      console.log(this.history.userID);
																      console.log(this.history);
															      		this.historyService.insert(this.history).subscribe(()=>{});	
																   }																	
																	})	  	
	}
	

  clear(){
    this.excel = new ExcelDTO();
  }

}
