import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { ExcelDTO } from 'src/dto/exceldto';
import { HttpClient } from '@angular/common/http';

import {map} from 'rxjs/operators';

import { Observable } from 'rxjs';
import { StringDTO } from 'src/dto/stringdto';

@Injectable({
  providedIn: 'root'
})
export class ExcelService extends AbstractService<ExcelDTO>{

  constructor(http: HttpClient) {
	super(http);
    this.type = 'excel';
 }
	
	type: string;
    port: string = '8080';

	preinsert(directory: string){
        console.log(this.http.post('http://localhost:' + this.port + '/' + this.type + '/preinsert', directory));
		return this.http.post('http://localhost:' + this.port + '/' + this.type + '/preinsert', directory)
				.map((res:Response) => (
       			res.text() //Convert response to a string
   				))
   				.subscribe(data => {console.log(data)});
    }

}