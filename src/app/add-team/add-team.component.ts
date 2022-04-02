import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Team } from '../dto/team';

@Component({
  selector: 'app-add-team',
  templateUrl: './add-team.component.html',
  styleUrls: ['./add-team.component.css']
})
export class AddTeamComponent implements OnInit {
  dialogTitle:string = 'Add new killer team';
  teamForm: FormGroup;

  constructor(private dialogRef: MatDialogRef<AddTeamComponent>) {
    this.teamForm = new FormGroup({
      teamId: new FormControl("", [Validators.required]),
      teamName: new FormControl("", [Validators.required]),
      teamSize: new FormControl("", [Validators.required]),
      startCave: new FormControl("", [Validators.required]),
    });
  }

  ngOnInit(): void {
  }

  save() {
    const team:Team = new Team(
      this.teamForm.controls['teamId'].value,
      this.teamForm.controls['teamName'].value,
      this.teamForm.controls['teamSize'].value,
      this.teamForm.controls['startCave'].value,
      ''
    )
    this.dialogRef.close(team);
  }

  close() {
    this.dialogRef.close();
  }

}
