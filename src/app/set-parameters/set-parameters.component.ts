import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { RequsetParams } from '../dto/request-params';

@Component({
  selector: 'app-set-parameters',
  templateUrl: './set-parameters.component.html',
  styleUrls: ['./set-parameters.component.css']
})
export class SetParametersComponent implements OnInit {
  dialogTitle:string = 'Set up request parameters';
  parametersForm: FormGroup;

  orderBy: string[] = ["dragon_id", "dragon_name", "coordinate_x", "coordinate_y", "dragon_creation_date", 
    "dragon_age", "dragon_color", "dragon_type", "dragon_character", "killer_name", "killer_passport", "killer_eye", "killer_hair"
  ];
  operands: string[] = ["<", ">", "="]
  colors: string[] = ['GREEN', 'BLACK', 'BLUE', 'BROWN'];
  characters: string[] = ['CUNNING', 'WISE', 'CHAOTIC_EVIL'];
  types: string[] = ['WATER', 'UNDERGROUND', 'FIRE'];

  constructor(private dialogRef:MatDialogRef<SetParametersComponent>) { 
    this.parametersForm = new FormGroup({
      orderBy: new FormControl("dragon_id"),
      size: new FormControl(5),
      page: new FormControl(0),

      dragon_id: new FormControl(),
      dragon_name: new FormControl(),
      coordinate_x: new FormControl(),
      coordinate_y: new FormControl(),
      dragon_creation_date: new FormControl(),
      dragon_age: new FormControl(),
      dragon_color: new FormControl(),
      dragon_type: new FormControl(),
      dragon_character: new FormControl(),
      killer_name: new FormControl(),
      killer_passport: new FormControl(),
      killer_eye: new FormControl(),
      killer_hair: new FormControl(),

      dragon_id_operand: new FormControl("="),
      dragon_name_operand: new FormControl("="),
      coordinate_x_operand: new FormControl("="),
      coordinate_y_operand: new FormControl("="),
      dragon_creation_date_operand: new FormControl("="),
      dragon_age_operand: new FormControl("="),
      dragon_color_operand: new FormControl("="),
      dragon_type_operand: new FormControl("="),
      dragon_character_operand: new FormControl("="),
      killer_name_operand: new FormControl("="),
      killer_passport_operand: new FormControl("="),
      killer_eye_operand: new FormControl("="),
      killer_hair_operand: new FormControl("=")
    });
  }

  ngOnInit(): void {
  }

  save() {
    let orderBy = this.parametersForm.controls['orderBy'].value;
    let pageSize = this.parametersForm.controls['size'].value;
    let pageNumber = this.parametersForm.controls['page'].value;

    let filters:string[] = [];
    this.getFilters(filters);
    
    console.log(this.parametersForm.controls['dragon_id_operand'].value);
    console.log(this.parametersForm.controls['dragon_id'].value);

    const parameters:RequsetParams = new RequsetParams(orderBy, pageSize, filters, pageNumber);

    this.dialogRef.close(parameters);
  }

  close() {
    this.dialogRef.close();
  }

  private getFilters(filters:string[]) {
    if(this.parametersForm.controls['dragon_id'].value != null) {
      filters.push(`dragon_id${this.parametersForm.controls['dragon_id_operand'].value}${this.parametersForm.controls['dragon_id'].value}`)
    }
    if(this.parametersForm.controls['dragon_name'].value != null) {
      filters.push(`dragon_name${this.parametersForm.controls['dragon_name_operand'].value}${this.parametersForm.controls['dragon_name'].value}`)
    }
    if(this.parametersForm.controls['coordinate_x'].value != null) {
      filters.push(`coordinate_x${this.parametersForm.controls['coordinate_x_operand'].value}${this.parametersForm.controls['coordinate_x'].value}`)
    }
    if(this.parametersForm.controls['coordinate_y'].value != null) {
      filters.push(`coordinate_y${this.parametersForm.controls['coordinate_y_operand'].value}${this.parametersForm.controls['coordinate_y'].value}`)
    }
    if(this.parametersForm.controls['dragon_creation_date'].value != null) {
      filters.push(`dragon_creation_date${this.parametersForm.controls['dragon_creation_date_operand'].value}${this.parametersForm.controls['dragon_creation_date'].value}`)
    }
    if(this.parametersForm.controls['dragon_age'].value != null) {
      filters.push(`dragon_age${this.parametersForm.controls['dragon_age_operand'].value}${this.parametersForm.controls['dragon_age'].value}`)
    }
    if(this.parametersForm.controls['dragon_color'].value != null) {
      filters.push(`dragon_color${this.parametersForm.controls['dragon_color_operand'].value}${this.parametersForm.controls['dragon_color'].value}`)
    }
    if(this.parametersForm.controls['dragon_type'].value != null) {
      filters.push(`dragon_type${this.parametersForm.controls['dragon_type_operand'].value}${this.parametersForm.controls['dragon_type'].value}`)
    }
    if(this.parametersForm.controls['dragon_character'].value != null) {
      filters.push(`dragon_character${this.parametersForm.controls['dragon_character_operand'].value}${this.parametersForm.controls['dragon_character'].value}`)
    }
    if(this.parametersForm.controls['killer_name'].value != null) {
      filters.push(`killer_name${this.parametersForm.controls['killer_name_operand'].value}${this.parametersForm.controls['killer_name'].value}`)
    }
    if(this.parametersForm.controls['killer_passport'].value != null) {
      filters.push(`killer_passport${this.parametersForm.controls['killer_passport_operand'].value}${this.parametersForm.controls['killer_passport'].value}`)
    }
    if(this.parametersForm.controls['killer_eye'].value != null) {
      filters.push(`killer_eye${this.parametersForm.controls['killer_eye_operand'].value}${this.parametersForm.controls['killer_eye'].value}`)
    }
    if(this.parametersForm.controls['killer_hair'].value != null) {
      filters.push(`killer_hair${this.parametersForm.controls['killer_hair_operand'].value}${this.parametersForm.controls['killer_hair'].value}`)
    }
  }

}
