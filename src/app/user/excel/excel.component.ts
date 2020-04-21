import { Component, OnInit } from '@angular/core';
import { ExcelService } from 'src/service/excel.service';
import { ExcelDTO } from 'src/dto/exceldto';
import { StringDTO } from 'src/dto/stringdto';
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
	
	par: number[] = [];
	par1:number = 0;
	par2:number = 0;
	par3:number = 0;
	par4:number = 0;

  constructor(private service: ExcelService) { }

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
																	this.preview = prwSpring, 
																	console.log("ritorno excelInsert: " + this.preview.titles[0])
																	})	  	
	}
	

  clear(){
    this.excel = new ExcelDTO();
  }

}
