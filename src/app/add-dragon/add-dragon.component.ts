import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSelect } from '@angular/material/select';
import { Coordinates } from '../dto/coordinates';
import { Dragon } from '../dto/dragon';
import { Person } from '../dto/person';

@Component({
  selector: 'app-add-dragon',
  templateUrl: './add-dragon.component.html',
  styleUrls: ['./add-dragon.component.css']
})
export class AddDragonComponent implements OnInit {
  dialogTitle:string = 'Add new dragon';
  dragonForm: FormGroup;

  colors: string[] = ['GREEN', 'BLACK', 'BLUE', 'BROWN'];
  characters: string[] = ['CUNNING', 'WISE', 'CHAOTIC_EVIL'];
  types: string[] = ['WATER', 'UNDERGROUND', 'FIRE'];

  constructor(private dialogRef:MatDialogRef<AddDragonComponent>) {
    this.dragonForm = new FormGroup({
      name: new FormControl("", [Validators.required]),
      x: new FormControl("", [Validators.required]),
      y: new FormControl("", [Validators.required]),
      creationDate: new FormControl(""),
      age: new FormControl("", [Validators.required]),
      color: new FormControl("", [Validators.required]),
      type: new FormControl("", [Validators.required]),
      character: new FormControl("", [Validators.required]),
      personName: new FormControl(""),
      passportId: new FormControl(""),
      eyeColor: new FormControl(""),
      hairColor: new FormControl("")
    });
  }

  ngOnInit(): void {
  }

  save() {
    const coordinates: Coordinates = new Coordinates(null, this.dragonForm.controls['x'].value, this.dragonForm.controls['y'].value)
    const killer: Person = new Person(
      null,
      this.dragonForm.controls['personName'].value,
      this.dragonForm.controls['passportId'].value,
      this.dragonForm.controls['eyeColor'].value,
      this.dragonForm.controls['hairColor'].value
    );

    const dragon: Dragon = new Dragon(
      null,
      this.dragonForm.controls['name'].value,
      coordinates,
      new Date(this.dragonForm.controls['creationDate'].value),
      this.dragonForm.controls['age'].value,
      this.dragonForm.controls['color'].value,
      this.dragonForm.controls['type'].value,
      this.dragonForm.controls['character'].value,
      killer
    );
    
    this.dialogRef.close(dragon);
  }

  close() {
    this.dialogRef.close();
  }

}
