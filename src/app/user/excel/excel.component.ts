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
	preview: StringDTO;

  constructor(private service: ExcelService) { }

  ngOnInit() {
    
  }

    preinsert(directoryLetta: string) {
	console.log(directoryLetta);
    this.service.preinsert(directoryLetta).subscribe((prwSpring) => {
																	this.preview = prwSpring, 
																	console.log(this.preview.titles[0]),
																	console.log(this.preview.line1[0]);
																	})
	
	  	
}

  clear(){
    this.excel = new ExcelDTO();
  }

}
