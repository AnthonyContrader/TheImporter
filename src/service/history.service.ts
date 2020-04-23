import { Injectable } from '@angular/core';
import { HistoryDTO } from 'src/dto/historyDTO';
import { Observable } from 'rxjs';
import { AbstractService } from './abstractservice';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HistoryService extends AbstractService<HistoryDTO> {

  constructor(http: HttpClient) { 
    super(http, "history");
    this.type = 'api';
  }

  delete(id: string): Observable<any> {
    return this.http.delete('http://localhost:' + this.port + '/history/' + 'api' + '/' + 'histories' + '/' + id, {
      headers: {
        Authorization: this.auth()
      }
    });
  }

  insert(dto: HistoryDTO): Observable<any> {
    return this.http.post('http://localhost:' + this.port + '/history/' + 'api' + '/' + 'histories', dto, {
      headers: {
        Authorization: this.auth()
      }
    });
  }

}
