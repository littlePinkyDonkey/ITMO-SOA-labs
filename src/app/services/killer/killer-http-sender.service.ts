import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Person } from 'src/app/dto/person';
import { Team } from 'src/app/dto/team';
import { secondUrl } from 'src/config';

@Injectable({
  providedIn: 'root'
})
export class KillerHttpSenderService {

  constructor(private http: HttpClient) { }

  getAllTeams() {
    return this.http.get<Array<Team>>(secondUrl)
  }

  createKillerTeam(teamId:number, teamName:string, teamSize:number, startCaveId:number) {
    let uri:string = `/teams/create/${teamId}/${teamName}/${teamSize}/${startCaveId}`
    return this.http.post(secondUrl + uri, '');
  }

  createKiller(killer:Person) {
    return this.http.post(secondUrl + '/create', killer);
  }

  sendKillerToCave(teamId:number, caveId:number) {
    let uri:string = `/team/${teamId}/move-to-cave/${caveId}`;
    return this.http.post(secondUrl + uri, '');
  }
  
}
