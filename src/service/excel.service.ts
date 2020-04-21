import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { ExcelDTO } from 'src/dto/exceldto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StringDTO } from 'src/dto/stringdto';
import { ProductDTO } from 'src/dto/productdto';
import { IntDTO } from 'src/dto/IntDTO';

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

	  getAll1(): Observable<ProductDTO[]> {

    return this.http.get<ProductDTO[]>('http://localhost:' + this.port + '/excel/' + 'api' + '/' + 'products' + '/', {
      headers: {
        Authorization: this.auth()
      }
    });
  }

  getProducts(numpage: number): Observable<ProductDTO[]> {

    return this.http.get<ProductDTO[]>('http://localhost:' + this.port + '/excel/' + 'api' + '/' + 'products?page='+numpage+'&size=5', {
      headers: {
        Authorization: this.auth()
      }
    });
  }

  getpages():Observable<IntDTO>{
    return this.http.get<IntDTO>('http://localhost:' + this.port + '/excel/' + 'api' + '/' + 'products/pages?size=5', {
      headers: {
        Authorization: this.auth()
      }
    });
  }

  delete(id: string): Observable<any> {
    return this.http.delete('http://localhost:' + this.port + '/excel/' + 'api' + '/' + 'products' + '/' + id, {
      headers: {
        Authorization: this.auth()
      }
    });
  }

  insert1(dto: ProductDTO): Observable<any> {
    return this.http.post('http://localhost:' + this.port + '/excel/' + 'api' + '/' + 'products' + '/', dto, {
      headers: {
        Authorization: this.auth()
      }
    });
  }

  update1(dto: ProductDTO): Observable<ProductDTO> {
    return this.http.put<ProductDTO>('http://localhost:' + this.port + '/excel/' + 'api' + '/' + 'products' + '/', dto, {
      headers: {
        Authorization: this.auth()
      }
    });
  }


}