import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Dragon } from 'src/app/dto/dragon';
import { baseUrl } from 'src/config';

@Injectable({
  providedIn: 'root'
})
export class DragonHttpSenderService {
  private date:string = "";

  constructor(private http: HttpClient) { }

  deleteDragon(id:number) {
    return this.http.delete(baseUrl + '/dragons', {
      params: {id: id}
    })
  }

  addDragon(dragon:Dragon) {    
    return this.http.post(baseUrl + '/dragons', dragon)
  }

  updateDragon(dragon:Dragon) {
    return this.http.put(baseUrl + '/dragons', dragon)
  }

  getAgeSum() {
    return this.http.get(baseUrl + '/sum')
  }

  getMinId() {
    return this.http.get<Dragon>(baseUrl + '/min')
  }

}
