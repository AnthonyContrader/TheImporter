import { Component, OnInit } from '@angular/core';
import { NgModel } from '@angular/forms';
import { UserService } from 'src/service/user.service';
import { UserDTO } from 'src/dto/userdto';
import { ManagedUserVM } from 'src/dto/signupdto';
import { JsonPipe } from '@angular/common';
import { ProductService } from 'src/service/product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sing-up',
  templateUrl: './sing-up.component.html',
  styleUrls: ['./sing-up.component.css']
})
export class SingUpComponent implements OnInit {
  checked: string = "";
  lenght: string = "";
  email: string = "";
  validuser: string = "";

  user: UserDTO;
  usertoinsert: ManagedUserVM;
  prova: string = "";

  constructor(private service: UserService) { }

  ngOnInit() {
    //this.service.login(null);
    this.checkEmail();
    this.checkLenght();
    this.checkUser();
  }


  checkPass(): boolean {
    var a = (<HTMLInputElement>document.getElementById("pass1")).value;
    var b = (<HTMLInputElement>document.getElementById("pass2")).value;
    if (a == b) {
      this.checked = ":uguali";
      return true;
    } else {
      this.checked = ":diverse";
      return false;
    }
  }
  checkLenght(): boolean {
    var a = (<HTMLInputElement>document.getElementById("pass1")).value;
    if (a != "") {
      if (a.length >= 8) {
        this.lenght = "";
        return true;
      } else {
        this.lenght = ":password corta";
        return false;
      }
    } else {
      this.lenght = ":campo richiesto";
    }
  }
  checkEmail(): boolean {
    var a = (<HTMLInputElement>document.getElementById("e-mail")).value;
    if (a != "") {
      if (a.indexOf("@") > 1 && a.indexOf(".") > 3) {
        this.email = "";
        return true;
      }
      else {
        this.email = ":e-mail non corretta";
        return false;
      }
    } else {
      this.email = ":campo richiesto";
      return false;
    }

  }
  checkUser(): boolean {
    var a = (<HTMLInputElement>document.getElementById("user")).value;
    if (a != "") {
      return true;
    } else {
      this.validuser = ":campo richiesto";
      return false;
    }
  }

  singup(): void {
    if (this.checkEmail() && this.checkLenght() && this.checkPass() && this.checkUser()) {
      this.usertoinsert = new ManagedUserVM();
      this.usertoinsert.email = (<HTMLInputElement>document.getElementById("e-mail")).value;
      this.usertoinsert.login = (<HTMLInputElement>document.getElementById("user")).value;
      this.usertoinsert.password = (<HTMLInputElement>document.getElementById("pass1")).value;
      this.usertoinsert.authorities[0] = "ROLE_USER";
      this.service.signup(this.usertoinsert).subscribe((token: any) => { console.log(token) });
    } else {
      alert("campi non inseriti o errati");
    }
  }
}
