import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Team } from '../dto/team';
import { KillerHttpSenderService } from '../services/killer/killer-http-sender.service';

@Component({
  selector: 'app-all-teams',
  templateUrl: './all-teams.component.html',
  styleUrls: ['./all-teams.component.css']
})
export class AllTeamsComponent implements OnInit {
  displayedColumns: string[] = ['id', 'name', 'size', 'cave']

  dialogTitle:string = 'Teams';
  teamsList:Array<Team>;

  constructor(private dialogRef:MatDialogRef<AllTeamsComponent>, private teamsSender:KillerHttpSenderService) {
    this.teamsList = new Array<Team>();
  }

  ngOnInit(): void {
    this.loadTeams();
  }

  private loadTeams() {
    this.teamsSender.getAllTeams().subscribe((data:Array<Team>) => {
      this.teamsList = data;
      console.log('t')
    });
  }

  close() {
    this.dialogRef.close();
  }

}
