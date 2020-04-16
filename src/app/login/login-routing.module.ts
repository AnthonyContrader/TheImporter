import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login.component';
import { SingUpComponent } from './sing-up/sing-up.component';

/**
 * Routing del LoginModule. gestisce il routing per le pagine di login 
 * (ed eventualmente di registrazione). Come ogni modulo di routing, viene 
 * importato nel suo modulo.
 * 
 * @see LoginModule
 * 
 * @author Vittorio Valent
 */
const routes: Routes = [
  { path:'login', component: LoginComponent },
  { path:'sing-up', component: SingUpComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class LoginRoutingModule { }
