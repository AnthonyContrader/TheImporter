import { Component, OnInit } from '@angular/core';
import { ExcelService } from 'src/service/excel.service';
import { ExcelDTO } from 'src/dto/exceldto';

@Component({
  selector: 'app-excel',
  templateUrl: './excel.component.html',
  styleUrls: ['./excel.component.css']
})
export class ExcelComponent implements OnInit {

	excel: ExcelDTO = new ExcelDTO();
  //producttoinsert: ProductDTO = new ProductDTO();
	directory: string;

  constructor(private service: ExcelService) { }

  ngOnInit() {
    
  }

    preinsert(directoryLetta: string) {
	console.log(directoryLetta);
    this.service.preinsert(directoryLetta).subscribe((dirSpring) => this.directory = dirSpring);
	waits(1);
	this.directory = this.directory+" ricevuto";
	
	console.log(this.directory);  	
}

  clear(){
    this.excel = new ExcelDTO();
  }

}
