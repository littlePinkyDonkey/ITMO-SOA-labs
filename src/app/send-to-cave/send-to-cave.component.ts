import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Move } from '../dto/send-to-cave';

@Component({
  selector: 'app-send-to-cave',
  templateUrl: './send-to-cave.component.html',
  styleUrls: ['./send-to-cave.component.css']
})
export class SendToCaveComponent implements OnInit {
  dialogTitle:string = 'Send killers to cave';
  sendForm: FormGroup;

  constructor(private dialogRef:MatDialogRef<SendToCaveComponent>) {
    this.sendForm = new FormGroup({
      teamId: new FormControl("", [Validators.required]),
      caveId: new FormControl("", [Validators.required])
    })
  }

  ngOnInit(): void {
  }

  save() {
    const move:Move = new Move(
      this.sendForm.controls['teamId'].value,
      this.sendForm.controls['caveId'].value
    );
    this.dialogRef.close(move);
  }

  close() {
    this.dialogRef.close();
  }

}
