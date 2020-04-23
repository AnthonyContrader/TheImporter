import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/service/product.service';
import { ProductDTO } from 'src/dto/productdto';
import { IntDTO } from 'src/dto/Intdto';
import { HistoryService } from 'src/service/history.service';
import { HistoryDTO } from 'src/dto/historyDTO';
import { UserDTO } from 'src/dto/userdto';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products: ProductDTO[];
  product: ProductDTO;
  products1: ProductDTO[];
  numPages:number;
  numbers:number[];
  pages:IntDTO;
  User:UserDTO;
  temp: ProductDTO[];
  producttoinsert: ProductDTO = new ProductDTO();
  history: HistoryDTO;

  constructor(private service: ProductService,private historyService: HistoryService) { }

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
    this.refresh();

  }

  update(product: ProductDTO) {
    this.service.update(product).subscribe(() => this.getProducts(0));
  }

  insert(product: ProductDTO) {
    this.service.insert(product).subscribe((product) => {
      this.product=product;
      this.User=new UserDTO;
      this.User=JSON.parse(localStorage.getItem('AUTOKEN'));
      console.log(this.product.id);
      this.history=new HistoryDTO(this.User.id,this.product.id);
      this.history.productID=this.product.id;
      this.history.userID=this.User.id;
      console.log(this.history.productID);
      console.log(this.history.userID);
      console.log(this.history);
      this.historyService.insert(this.history).subscribe(()=>{});
    });
    //this.refresh();
  }

  clear(){
    this.producttoinsert = new ProductDTO();
  }

  refresh(): void {
    window.location.reload();
}
}
