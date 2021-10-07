import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DragonHttpSenderService {

  constructor(private hhtp: HttpClient) { }

  test() {
    console.log('teasfa');
  }
}
