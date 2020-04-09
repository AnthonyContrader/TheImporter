import { Component, OnInit } from '@angular/core';
import { ExcelService } from 'src/service/excel.service';
import { ExcelDTO } from 'src/dto/exceldto';
import { StringDTO } from 'src/dto/stringdto';

@Component({
  selector: 'app-excel',
  templateUrl: './excel.component.html',
  styleUrls: ['./excel.component.css']
})
export class ExcelComponent implements OnInit {

	excel: ExcelDTO = new ExcelDTO();
  //producttoinsert: ProductDTO = new ProductDTO();
	directory: string;
	temp: StringDTO;

  constructor(private service: ExcelService) { }

  ngOnInit() {
    
  }

    preinsert(directoryLetta: string) {
	console.log(directoryLetta);
    this.service.preinsert(directoryLetta).subscribe((dirSpring) => this.temp = dirSpring);
	
	console.log(this.temp+"ricevuto");  	
}

  clear(){
    this.excel = new ExcelDTO();
  }

}
