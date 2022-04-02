import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cave } from 'src/app/dto/cave';
import { secondUrl } from 'src/config';

@Injectable({
  providedIn: 'root'
})
export class CaveHttpSenderService {

  constructor(private http:HttpClient) { }

  createCave() {
    return this.http.post(secondUrl + '/cave/create', '');
  }

  getAllCaves() {
    return this.http.get<Array<Cave>>(secondUrl + '/cave');
  }
}
