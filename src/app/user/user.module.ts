import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { UserRoutingModule } from './user-routing.module';
import { ProductsComponent } from './products/products.component';
import { ExcelComponent } from './excel/excel.component';

@NgModule({
  declarations: [UserDashboardComponent, ProductsComponent, ExcelComponent],
  imports: [
    CommonModule,
    UserRoutingModule,
	FormsModule
  ]
})
export class UserModule { }
