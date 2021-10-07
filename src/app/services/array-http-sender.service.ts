import { Injectable, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Dragon } from '../dto/dragon';

@Injectable({
  providedIn: 'root'
})
export class ArrayHttpSenderService {

  constructor(private http: HttpClient) { }

  getAllElements() {
    return this.http.get<Array<Dragon>>('http://localhost:16716/main');
  }
}
