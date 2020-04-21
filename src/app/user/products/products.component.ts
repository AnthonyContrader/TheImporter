import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/service/product.service';
import { ProductDTO } from 'src/dto/productdto';
import { IntDTO } from 'src/dto/IntDTO';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products: ProductDTO[];
  products1: ProductDTO[];
  numPages:number;
  numbers:number[];
  pages:IntDTO;
  temp: ProductDTO[];
  producttoinsert: ProductDTO = new ProductDTO();

  constructor(private service: ProductService) { }

  ngOnInit() {
    this.getProducts(0);
    this.getAllPages();
    
  }

  getAllPages(){
    this.service.getpages().subscribe(products1 =>{this.pages = products1
      this.numPages=this.pages.num 
      this.numbers=[];
      for (let i = 0; i < this.numPages ; i++) {
        this.numbers.push(i);
      }
      
    } )
  }

  getProducts(numpage:number) {
    this.service.getProducts(numpage).subscribe(products => this.products = products);
  }

  delete(product: ProductDTO) {
    this.service.delete(product.id.toString()).subscribe(() => this.getProducts(0));
  }

  update(product: ProductDTO) {
    this.service.update(product).subscribe(() => this.getProducts(0));
  }

  insert(product: ProductDTO) {
    this.service.insert(product).subscribe(() => this.getProducts(0));
  }

  clear(){
    this.producttoinsert = new ProductDTO();
  }

}
