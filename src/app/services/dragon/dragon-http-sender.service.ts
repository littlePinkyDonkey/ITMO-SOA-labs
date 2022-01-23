import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Dragon } from 'src/app/dto/dragon';

@Injectable({
  providedIn: 'root'
})
export class DragonHttpSenderService {
  private date:string = "";

  constructor(private http: HttpClient) { }

  deleteDragon(id:number) {
    return this.http.delete('http://localhost:16716/dragons', {
      params: {id: id}
    })
  }

  addDragon(dragon:Dragon) {
    let temp = JSON.parse(JSON.stringify(dragon));
    
    this.date = dragon.creationDate?.toString();

    if(this.date !== '') {
      let array = this.date.split('T1');
      let dateArray = array[0].split('-');
      let timeArray = array[1].split(':');

      temp.creationDate = {
        "date": {
          "year": dateArray[0],
          "month": dateArray[1],
          "day": dateArray[2]
        },
        "time": {
            "hour": timeArray[0],
            "minute": timeArray[1],
            "second": 0,
            "nano": 0
        }
      }
    } else {
      temp.creationDate = null;
    }
    
    return this.http.post('http://localhost:16716/dragons', temp)
  }

  updateDragon(dragon:String) {
    return this.http.put('http://localhost:16716/dragons', dragon)
  }

}
