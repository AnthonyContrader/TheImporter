import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { ExcelDTO } from 'src/dto/exceldto';
import { HttpClient } from '@angular/common/http';
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

	preinsert(directory: string): Observable<StringDTO> {
		return this.http.post<any>('http://localhost:' + this.port + '/' + this.type + '/preinsert', directory);
    }

	excelInsert(par: number[]): Observable<StringDTO> {
		return this.http.post<any>('http://localhost:' + this.port + '/' + this.type + '/excelInsert', par);
    }

}