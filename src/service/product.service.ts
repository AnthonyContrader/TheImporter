import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { ProductDTO } from 'src/dto/productdto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserDTO } from 'src/dto/userdto';
import { IntDTO } from 'src/dto/IntDTO';



/**
 * I service sono decorati da @Injectable. 
 * Qui trovate, oltre ai metodi ereditati dall'Abstract,
 *  il metodo per il login (in mirror con il backend).
 * 
 * @author Vittorio Valent
 * 
 * @see AbstractService
 */
@Injectable({
  providedIn: 'root'
})
export class ProductService extends AbstractService<ProductDTO>{

  constructor(http: HttpClient) {
    super(http, "product");
    this.type = 'api';
  }




  getAll(): Observable<ProductDTO[]> {

    return this.http.get<ProductDTO[]>('http://localhost:' + this.port + '/product/' + 'api' + '/' + 'products' + '/', {
      headers: {
        Authorization: this.auth()
      }
    });
  }

  getProducts(numpage: number): Observable<ProductDTO[]> {

    return this.http.get<ProductDTO[]>('http://localhost:' + this.port + '/product/' + 'api' + '/' + 'products?page='+numpage+'&size=5', {
      headers: {
        Authorization: this.auth()
      }
    });
  }

  getpages():Observable<IntDTO>{
    return this.http.get<IntDTO>('http://localhost:' + this.port + '/product/' + 'api' + '/' + 'products/pages?size=5', {
      headers: {
        Authorization: this.auth()
      }
    });
  }

  delete(id: string): Observable<any> {
    return this.http.delete('http://localhost:' + this.port + '/product/' + 'api' + '/' + 'products' + '/' + id, {
      headers: {
        Authorization: this.auth()
      }
    });
  }

  insert(dto: ProductDTO): Observable<any> {
    return this.http.post('http://localhost:' + this.port + '/product/' + 'api' + '/' + 'products' + '/', dto, {
      headers: {
        Authorization: this.auth()
      }
    });
  }

  update(dto: ProductDTO): Observable<ProductDTO> {
    return this.http.put<ProductDTO>('http://localhost:' + this.port + '/product/' + 'api' + '/' + 'products' + '/', dto, {
      headers: {
        Authorization: this.auth()
      }
    });
  }


}
