import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { ExcelDTO } from 'src/dto/exceldto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StringDTO } from 'src/dto/stringdto';
import { ProductDTO } from 'src/dto/productdto';

@Injectable({
  providedIn: 'root'
})
export class ExcelService extends AbstractService<ExcelDTO>{

  constructor(http: HttpClient) {
	super(http, "excel");
    this.type = 'excel';
 }
	
	type: string;
    port: string = '8080';

	preinsert(directory: string): Observable<StringDTO> {
		return this.http.post<any>('http://localhost:8080/excel/api/preinsert', directory, {
            headers: {
              Authorization : this.auth()
         }
        });
    }

	excelInsert(par: number[]): Observable<ProductDTO[]> {
		return this.http.post<any>('http://localhost:8080/excel/api/excelInsert', par, {
            headers: {
              Authorization : this.auth()
         }
        });
    }

}