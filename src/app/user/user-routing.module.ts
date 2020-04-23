import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserLayoutComponent } from '../layout/user-layout/user-layout.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { ProductsComponent } from './products/products.component';
import { ProductsexcelComponent } from './productsexcel/productsexcel.component';
import { ExcelComponent } from './excel/excel.component';
import { HistoryComponent } from './history/history.component';
import { CommonModule } from '@angular/common';

//dovrai imporate i componenti di product e excel

const routes: Routes = [
  { path: 'user-dashboard', component: UserLayoutComponent, children:[
    { path: '', component: UserDashboardComponent},
	{ path: 'products', component: ProductsComponent},
	{ path: 'productsexcel', component: ProductsexcelComponent},
	{ path: 'excel', component: ExcelComponent},
	{ path: 'history', component: HistoryComponent}
    //qua dentro ci dovrai mettere product e excel
    
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
