import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-delete-dragons-by-char',
  templateUrl: './delete-dragons-by-char.component.html',
  styleUrls: ['./delete-dragons-by-char.component.css']
})
export class DeleteDragonsByCharComponent implements OnInit {
  dialogTitle:string = 'Delete by character';
  deleteForm: FormGroup;

  characters: string[] = ['CUNNING', 'WISE', 'CHAOTIC_EVIL'];

  constructor(private dialogRef:MatDialogRef<DeleteDragonsByCharComponent>) {
    this.deleteForm = new FormGroup({
      character: new FormControl("", [Validators.required])
    });
   }

  ngOnInit(): void {
  }

  save() {
    let character = this.deleteForm.controls['character'].value;
    this.dialogRef.close(character);
  }

  close() {
    this.dialogRef.close();
  }

}
