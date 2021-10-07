import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Coordinates } from '../dto/coordinates';
import { Person } from '../dto/person';
import { DialogData } from '../dto/dialog-data';

@Component({
  selector: 'app-add-dragon',
  templateUrl: './add-dragon.component.html',
  styleUrls: ['./add-dragon.component.css']
})
export class AddDragonComponent implements OnInit {
  dialogTitle:string = 'Add new dragon';

  constructor(private dialogRef:MatDialogRef<AddDragonComponent>, @Inject(MAT_DIALOG_DATA) public data:DialogData) {}

  ngOnInit(): void {
    
  }

  save() {
    this.dialogRef.close(this.data);
  }

  close() {
    this.dialogRef.close();
  }

}
