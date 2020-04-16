import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/service/product.service';
import { ProductDTO } from 'src/dto/productdto';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products: ProductDTO[];
  temp: ProductDTO[];
  producttoinsert: ProductDTO = new ProductDTO();

  constructor(private service: ProductService) { }

  ngOnInit() {
    this.getProducts();
	
  }

  getProducts() {
	
	console.log(localStorage.getItem("key"));
    this.service.getAll().subscribe(products => this.products = products);
  }

  delete(product: ProductDTO) {
    this.service.delete(product.id.toString()).subscribe(() => this.getProducts());
  }

  update(product: ProductDTO) {
    this.service.update(product).subscribe(() => this.getProducts());
  }

  insert(product: ProductDTO) {
    this.service.insert(product).subscribe(() => this.getProducts());
  }

  clear(){
    this.producttoinsert = new ProductDTO();
  }
}
