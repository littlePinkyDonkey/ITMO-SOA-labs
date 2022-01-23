import { Injectable, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Location } from '@angular/common';

import { Dragon } from '../dto/dragon';

@Injectable({
  providedIn: 'root'
})
export class ArrayHttpSenderService {
  private uri:String = '/';

  constructor(private http: HttpClient, private location: Location) { }

  getAllElements() {
    var uri = '?order_by=dragon_name';
    this.location.go(uri);
    return this.http.get<Array<Dragon>>('http://localhost:16716/' + uri);
  }
}
