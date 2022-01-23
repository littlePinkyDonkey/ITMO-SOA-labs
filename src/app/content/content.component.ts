import { Component, OnInit } from '@angular/core';
import { ArrayHttpSenderService } from '../services/array-http-sender.service';
import { MatDialog, MatDialogConfig, MatDialogRef } from '@angular/material/dialog';

import { Dragon } from '../dto/dragon';
import { AddDragonComponent } from '../add-dragon/add-dragon.component';
import { EditDragonComponent } from '../edit-dragon/edit-dragon.component';
import { DialogData } from '../dto/dialog-data';
import { DragonHttpSenderService } from '../services/dragon/dragon-http-sender.service';
import { ActivatedRoute } from '@angular/router';
import { Person } from '../dto/person';
import { SetParametersComponent } from '../set-parameters/set-parameters.component';
import { RequsetParams } from '../dto/request-params';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {
  displayedColumns: string[] = ['id', 'name', 'coordinates', 'creation', 'age', 'color', 'type', 'character', 'killer', 'delete', 'edit'];
  dragonList:Array<Dragon>;

  formData:DialogData = new DialogData();

  constructor(private arraySender: ArrayHttpSenderService,
              private dragonSender: DragonHttpSenderService,
              private dialog: MatDialog,
              private route: ActivatedRoute) {
    this.dragonList = new Array<Dragon>();
    this.route.queryParams.subscribe(params => {
      
    });
  }

  ngOnInit(): void {
    this.loadDragons();
  }

  private loadDragons() {
    this.arraySender.getAllElements('/?order_by=dragon_id&size=5&page=0').subscribe((data: Array<Dragon>) => {
      this.dragonList = data;
    });
  }

  deleteDragon(id: number) {
    this.dragonSender.deleteDragon(id).subscribe({
      next: data => {
        console.log(data);
        this.loadDragons();
      },
      error: error => {
        console.log(error);
      }
    })
  }

  editDragon(dragon: Dragon) {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.data = dragon;

    const dialogRef = this.dialog.open(EditDragonComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(data => {
        if(data != undefined) {
          const newVal: Dragon = data;

          console.log('new = ', data);
          console.log('old = ', dragon)
          newVal.id = dragon.id;
          newVal.coordinates.coordinatesId = dragon.coordinates.coordinatesId;
          if (dragon.killer != null) {
            newVal.killer!.personId = dragon.killer?.personId;
          }

          let temp = JSON.parse(JSON.stringify(newVal));
          if(newVal.creationDate?.toString().includes('T1')) {
            let date:String = newVal.creationDate?.toString();

            if(date !== '') {
              let array = date.split('T1');
              let dateArray = array[0].split('-');
              let timeArray = array[1].split(':');

              temp.creationDate = {
                "date": {
                  "year": dateArray[0],
                  "month": dateArray[1],
                  "day": dateArray[2]
                },
                "time": {
                    "hour": timeArray[0],
                    "minute": timeArray[1],
                    "second": 0,
                    "nano": 0
                }
              }
            } else {
              temp.creationDate = null;
            }
          }

          this.dragonSender.updateDragon(temp).subscribe({
            next: data => {
              this.loadDragons();
            },
            error: error => {
              console.error(error);
            }
          });
        }
    });
  }

  addDragon() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(AddDragonComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(data => {
      if(data !== undefined) {
        
        const dragon:Dragon = data;
        this.dragonSender.addDragon(dragon).subscribe({
          next: data => {
            this.loadDragons();
          },
          error: error => {
            console.log(error);
          }
        })
      }
    });
  }

  setParameters() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(SetParametersComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(data => {
      if(data != undefined) {
        const params:RequsetParams = data;
        console.log(params);
        console.log(JSON.stringify(params.filter_by))
        this.arraySender.getWithparams(params).subscribe((data: Array<Dragon>) => {
          this.dragonList = data;
        });
      }
    })
  }

  resetParameters() {
    this.arraySender.getAllElements('/?order_by=dragon_id&size=5&page=0').subscribe((data: Array<Dragon>) => {
      this.dragonList = data;
    });
  }

}
