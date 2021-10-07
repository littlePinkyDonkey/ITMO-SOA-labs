import { Component, OnInit } from '@angular/core';
import { ArrayHttpSenderService } from '../services/array-http-sender.service';
import { MatDialog, MatDialogConfig, MatDialogRef } from '@angular/material/dialog';

import { Dragon } from '../dto/dragon';
import { AddDragonComponent } from '../add-dragon/add-dragon.component';
import { DialogData } from '../dto/dialog-data';
import { DragonHttpSenderService } from '../services/dragon/dragon-http-sender.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {
  displayedColumns: string[] = ['id', 'name', 'coordinates', 'creation', 'age', 'color', 'type', 'character', 'killer', 'delete', 'edit'];
  dragonList:Array<Dragon>;

  formData:DialogData = new DialogData();

  constructor(private arraySender: ArrayHttpSenderService, private dragonSender: DragonHttpSenderService, private dialog: MatDialog) {
    this.dragonList = new Array<Dragon>();
  }

  ngOnInit(): void {
    this.loadDragons();
  }

  print() {
    console.log(this.dragonList);
  }

  private loadDragons() {
    this.arraySender.getAllElements().subscribe((data: Array<Dragon>) => {
      this.dragonList = data;
    });
  }

  deleteDragon(id: number) {
    this.dragonList.forEach(d => {
      if(d.id === id) {
        this.dragonList = [...this.dragonList.slice(0, this.dragonList.indexOf(d)), ...this.dragonList.slice(this.dragonList.indexOf(d)+1, this.dragonList.length)]
      }
    });
  }

  editDragon(dragon: Dragon) {
    console.log(dragon);
  }

  openDialog() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.data = this.formData;

    const dialogRef = this.dialog.open(AddDragonComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(data => {
      console.log(data);
      if(data !== undefined && data.coordinates != null || data.killer != null ) {
        this.dragonSender.test();
      }
    });
  }

}
