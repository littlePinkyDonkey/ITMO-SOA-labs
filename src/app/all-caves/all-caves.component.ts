import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Cave } from '../dto/cave';
import { Team } from '../dto/team';
import { CaveHttpSenderService } from '../services/caves/cave-http-sender.service';

@Component({
  selector: 'app-all-caves',
  templateUrl: './all-caves.component.html',
  styleUrls: ['./all-caves.component.css']
})
export class AllCavesComponent implements OnInit {
  displayedColumns: string[] = ['id', 'team-id', 'team-name']

  dialogTitle:string = 'Caves';
  caveList:Array<Cave>;

  constructor(private dialogRef:MatDialogRef<AllCavesComponent>, private caveSender:CaveHttpSenderService) { 
    this.caveList = new Array<Cave>();
  }

  ngOnInit(): void {
    this.loadCaves();
  }

  private loadCaves() {
    this.caveSender.getAllCaves().subscribe((data:Array<Cave>) => {
      this.caveList = data;
    });
  }

  getTeamsIds(data:Cave) {
    let result:string = '';
    data.teams.forEach(element => {
      result+=`${element.teamId},`;
    });
    return result;
  }

  getTeamsNames(data:Cave) {
    let result:string = '';
    data.teams.forEach(element => {
      result+=`${element.teamName},`
    });
    return result;
  }

  close() {
    this.dialogRef.close();
  }

}
