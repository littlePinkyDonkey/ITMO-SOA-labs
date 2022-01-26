import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Dragon } from 'src/app/dto/dragon';
import { baseUrl } from 'src/config';
import { Location } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class DragonHttpSenderService {
  private date:string = "";

  constructor(private http: HttpClient, private location: Location) { }

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
    this.location.go('/min');
    return this.http.get<Dragon>(baseUrl + '/min')
  }

  removeByCharacter(character:string) {
    return this.http.delete(baseUrl + '/remove', {
      params: {character: character}
    });
  }

}
