import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Coordinates } from '../dto/coordinates';
import { Dragon } from '../dto/dragon';
import { Person } from '../dto/person';

@Component({
  selector: 'app-edit-dragon',
  templateUrl: './edit-dragon.component.html',
  styleUrls: ['./edit-dragon.component.css']
})
export class EditDragonComponent implements OnInit {
  dialogTitle:string = 'Edit dragon';
  dragonForm: FormGroup;

  colors: string[] = ['GREEN', 'BLACK', 'BLUE', 'BROWN'];
  characters: string[] = ['CUNNING', 'WISE', 'CHAOTIC_EVIL'];
  types: string[] = ['WATER', 'UNDERGROUND', 'FIRE'];

  constructor(private dialogRef:MatDialogRef<EditDragonComponent>, @Inject(MAT_DIALOG_DATA) public data: Dragon) {
    this.dragonForm = new FormGroup({
      name: new FormControl(data.name, [Validators.required]),
      x: new FormControl(data.coordinates.x, [Validators.required]),
      y: new FormControl(data.coordinates.y, [Validators.required]),
      creationDate: new FormControl(data.creationDate),
      age: new FormControl(data.age, [Validators.required]),
      color: new FormControl(data.color, [Validators.required]),
      type: new FormControl(data.type, [Validators.required]),
      character: new FormControl(data.character, [Validators.required]),
      personName: new FormControl(data.killer?.name),
      passportId: new FormControl((<any>data.killer)?.passportId),
      eyeColor: new FormControl(data.killer?.eyeColor),
      hairColor: new FormControl(data.killer?.hairColor)
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
