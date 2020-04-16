import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { ProductDTO } from 'src/dto/productdto';
import { HttpClient } from '@angular/common/http';



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

 

}
