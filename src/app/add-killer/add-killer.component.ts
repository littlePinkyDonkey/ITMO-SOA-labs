import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Person } from '../dto/person';

@Component({
  selector: 'app-add-killer',
  templateUrl: './add-killer.component.html',
  styleUrls: ['./add-killer.component.css']
})
export class AddKillerComponent implements OnInit {
  dialogTitle:string = 'Add new killer';
  killerForm: FormGroup;

  colors: string[] = ['GREEN', 'BLACK', 'BLUE', 'BROWN'];

  constructor(private dialogRef: MatDialogRef<AddKillerComponent>) {
    this.killerForm = new FormGroup({
      name: new FormControl("", [Validators.required]),
      passportId: new FormControl("", [Validators.required]),
      eyeColor: new FormControl("", [Validators.required]),
      hairColor: new FormControl("", [Validators.required])
    });
   }

  ngOnInit(): void {
  }

  save() {
    const killer: Person = new Person(
      null,
      this.killerForm.controls['name'].value,
      this.killerForm.controls['passportId'].value,
      this.killerForm.controls['eyeColor'].value,
      this.killerForm.controls['hairColor'].value
    );
    this.dialogRef.close(killer);
  }

  close() {
    this.dialogRef.close();
  }

}
