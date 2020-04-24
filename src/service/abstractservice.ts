import { Service } from './service';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { UserDTO } from 'src/dto/userdto';

/**
 * Service astratto, implementa tutti i metodi CRUD inviando request al server di SpringBoot. 
 * @param port il port del backend
 * @param type la mappatura del controller di ciascuna entità.
 * 
 * @see Service
 * 
 * @author Vittorio Valent
 */
export abstract class AbstractService<DTO> implements Service<DTO> {

    name: string = "api";
    port: string = '8080';
	type: string = "";

    constructor(protected http: HttpClient, urlType: string ) {
		this.type=urlType;
    }

    auth() {
        
		const key = localStorage.getItem('key');
		//console.log("key temp =" + key);
        if (key) {
          //console.log('Bearer ' + key);
          return 'Bearer ' + key;
        } else {
          return '';
        }
    
      }

    getAll(): Observable<DTO[]> {
		
        return this.http.get<DTO[]>('http://localhost:' + this.port + '/'+ this.name + '/' + this.type , {
            headers: {
              Authorization : this.auth()
         }
        });
     }

    read(id: number): Observable<DTO> { //Ritorno del server 
        return this.http.get<DTO>('http://localhost:' + this.port + '/'+ this.name + '/' + 'api' + '/' + this.type + id, {
          headers: {
            Authorization : this.auth()
       }
      });
    }

    delete(id: string): Observable<any> {
        return this.http.delete('http://localhost:' + this.port + '/' + this.name + '/' + this.type +'/'+ id, {
          headers: {
            Authorization : this.auth()
       }
      });
    }

    insert(dto: DTO): Observable<any> {
        return this.http.post('http://localhost:' + this.port + '/' + this.name + '/' + 'api' + '/'  + this.type, dto, {
          headers: {
            Authorization : this.auth()
       }
      });
    }

    update(dto: DTO): Observable<DTO> {
        return this.http.put<DTO>('http://localhost:' + this.port + '/' + this.name +  '/'  + this.type, dto, {
          headers: {
            Authorization : this.auth()
       }
      });
    }

}