import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { UserDTO } from 'src/dto/userdto';
import { HttpClient } from '@angular/common/http';
import { LoginDTO } from 'src/dto/logindto';
import { ManagedUserVM } from 'src/dto/signupdto';

import { Observable } from 'rxjs';

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
export class UserService extends AbstractService<UserDTO>{

  constructor(http: HttpClient) {
    super(http,"users");
    
  }

  auth() {
    const user = JSON.parse(localStorage.getItem('currentUser')) as UserDTO;
	console.log(user.authorities);
    if (user) {
      return 'Bearer ' + user.authorities;
    } else {
      return '';
    }
  }

  userLogged(username: string) {
     return this.http.get('http://localhost:8080/api/users/' + username, {
       headers: {
         Authorization: this.auth()
       }
     });
   }

  login(loginDTO: LoginDTO): Observable<UserDTO> {
    return this.http.post<any>('http://localhost:8080/' + this.name + '/authenticate', loginDTO);
  }
  signup(signupDTO: ManagedUserVM):Observable<UserDTO> {
    return this.http.post<any>('http://localhost:8080/api/register',signupDTO);
  }

  read(id: number): Observable<UserDTO> { //Ritorno del server 
    return this.http.get<UserDTO>('http://localhost:' + this.port + '/api/Users' + this.type + id, {
      headers: {
        Authorization : this.auth()
   }
  });
}
}
