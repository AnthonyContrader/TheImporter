import { Component, OnInit } from '@angular/core';
import { NgModel } from '@angular/forms';
import { UserService } from 'src/service/user.service';
import { UserDTO } from 'src/dto/userdto';

@Component({
  selector: 'app-sing-up',
  templateUrl: './sing-up.component.html',
  styleUrls: ['./sing-up.component.css']
})
export class SingUpComponent implements OnInit {
  checked: string = "";
  lenght: string = "";
  email: string = "";
  validuser: string="";
  check: boolean;

  users: UserDTO[];
  usertoinsert: UserDTO = new UserDTO();

  constructor(private service: UserService) { }

  ngOnInit() {
  }


  checkPass(): void {
    var a = (<HTMLInputElement>document.getElementById("pass1")).value;
    var b = (<HTMLInputElement>document.getElementById("pass2")).value;
    if (a == b) {
      this.checked = "uguali";
    } else {
      this.checked = "diverse";
    }
  }
  checkLenght(): void {
    var a = (<HTMLInputElement>document.getElementById("pass1")).value;
    if (a.length >= 8) {
      this.lenght = "";
    } else {
      this.lenght = "password corta";
    }
  }
  checkEmail(): void {
    var a = (<HTMLInputElement>document.getElementById("e-mail")).value;
    if (a.indexOf("@") > 1 && a.indexOf(".") > 3) {
      this.email = "";
    }
    else {
      this.email = "e-mail non corretta";
    }

  }
  checkUser(): void {
    var a = (<HTMLInputElement>document.getElementById("user")).value;
    this.service.getAll().subscribe(users => this.users = users);
    this.users.forEach(user => {
      if(user.login ==a){
      this.check=false;
      }
    });
    if(this.check){
      this.validuser="";
    }else{
      this.validuser="username in utlizzo";
    }
  }

  singup():void{

  }


}
