import { Component, OnInit } from '@angular/core';
import { LoginDTO } from 'src/dto/logindto';
import { NgForm } from '@angular/forms';
import { UserService } from 'src/service/user.service';
import { Router } from '@angular/router';
import { UserDTO } from 'src/dto/userdto';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginDTO: LoginDTO;

  constructor(private service: UserService, private router: Router) { }

  ngOnInit() {
  }

  login(f: NgForm): void {
   /* this.loginDTO = new LoginDTO(f.value.username, f.value.password);

    this.service.login(this.loginDTO).subscribe((user) => {

      if (user != null) {
        localStorage.setItem('currentUser', JSON.stringify(user));

        switch (user.usertype.toString()) {
          case 'ADMIN': {
            this.router.navigate(['/admin-dashboard']);
            break;
          }
          case 'USER': {
            this.router.navigate(['/user-dashboard']);
            break;
          }
          default:
            this.router.navigate(['/login']);
        }
      }
    });
  }*/
 	this.loginDTO = new LoginDTO(f.value.username, f.value.password);
    this.service.login(this.loginDTO).subscribe((token:any) => {
		localStorage.setItem("AUTOKEN", JSON.stringify({ "authorities": token.id_token }));
		console.log("token.id_token = " + token.id_token);
      	localStorage.setItem("currentUser", JSON.stringify({ "authorities": token.id_token }));
		localStorage.setItem("key", token.id_token);  //questo lo salva effettivamente in sessione globale
      this.service.userLogged(this.loginDTO.username).subscribe((user:UserDTO)=>{

        if (user != null) {
          localStorage.setItem('AUTOKEN', JSON.stringify(user));
          if(user.authorities[0] == "ROLE_ADMIN" ) {
            this.router.navigate(['/admin-dashboard']);
          }
          if(user.authorities[0] == "ROLE_USER") {
            this.router.navigate(['/user-dashboard']);
          }
        }else{
          this.router.navigate(['/login']);
          }
        });
      });
      } 
      
      singUp(): void{
        console.log("sono prima la chiamata del service");
        this.service.signup(null);
      console.log("sono dopo la chiamata del service");
        this.router.navigate(['/sing-up']);
      }
}
