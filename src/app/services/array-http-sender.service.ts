import { Injectable, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Location } from '@angular/common';

import { Dragon } from '../dto/dragon';
import { RequsetParams } from '../dto/request-params';
import { baseUrl } from 'src/config';

@Injectable({
  providedIn: 'root'
})
export class ArrayHttpSenderService {

  constructor(private http: HttpClient, private location: Location) { }

  getAllElements(uri:string) {
    this.location.go(uri);
    return this.http.get<Array<Dragon>>(baseUrl + uri);
  }

  getWithparams(params: RequsetParams) {
    let uri:string;
    if(params.filter_by != null) {
      uri = `?order_by=${params.order_by}&size=${params.size}&filter_by=${JSON.stringify(params.filter_by)}&page=${params.page}`;
    } else {
      uri = `?order_by=${params.order_by}&size=${params.size}&page=${params.page}`;
    }
    return this.getAllElements(uri);
  }
}
