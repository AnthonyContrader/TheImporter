import { Injectable } from '@angular/core';
import { HistoryDTO } from 'src/dto/historyDTO';
import { Observable } from 'rxjs';
import { AbstractService } from './abstractservice';
import { HttpClient } from '@angular/common/http';
import { LongDTO } from 'src/dto/longdto';

@Injectable({
  providedIn: 'root'
})
export class HistoryService extends AbstractService<HistoryDTO> {

  constructor(http: HttpClient) { 
    super(http, "history");
    this.type = 'api';
  }
  getAll():Observable<HistoryDTO[]>{
    return this.http.get<HistoryDTO[]>('http://localhost:' + this.port + '/history/' + 'api' + '/' + 'histories' + '/', {
      headers: {
        Authorization: this.auth()
      }
    });
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
  SearchByProduct(productId: number): Observable<LongDTO> {
    return this.http.post<LongDTO>('http://localhost:' + this.port + '/history/' + 'api' + '/' + 'history/SearchByProduct?ProductID='+productId, {
      headers: {
        Authorization: this.auth()
      }
    });
  }
  SearchByUser(Userid: number): Observable<LongDTO> {
    return this.http.get<LongDTO>('http://localhost:' + this.port + '/history/' + 'api' + '/' + 'history/SearchByUser?UserID='+ Userid, {
      headers: {
        Authorization: this.auth()
      }
    });
  }

}
