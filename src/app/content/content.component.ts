import { Component, OnInit } from '@angular/core';
import { ArrayHttpSenderService } from '../services/array-http-sender.service';
import { MatDialog, MatDialogConfig, MatDialogRef } from '@angular/material/dialog';

import { Dragon } from '../dto/dragon';
import { AddDragonComponent } from '../add-dragon/add-dragon.component';
import { EditDragonComponent } from '../edit-dragon/edit-dragon.component';
import { AddKillerComponent } from '../add-killer/add-killer.component';
import { DialogData } from '../dto/dialog-data';
import { DragonHttpSenderService } from '../services/dragon/dragon-http-sender.service';
import { ActivatedRoute } from '@angular/router';
import { Person } from '../dto/person';
import { SetParametersComponent } from '../set-parameters/set-parameters.component';
import { RequsetParams } from '../dto/request-params';
import { DeleteDragonsByCharComponent } from '../delete-dragons-by-char/delete-dragons-by-char.component';
import { HttpErrorResponse } from '@angular/common/http';
import { KillerHttpSenderService } from '../services/killer/killer-http-sender.service';
import { CaveHttpSenderService } from '../services/caves/cave-http-sender.service';
import { AddTeamComponent } from '../add-team/add-team.component';
import { Team } from '../dto/team';
import { SendToCaveComponent } from '../send-to-cave/send-to-cave.component';
import { Move } from '../dto/send-to-cave';
import { AllTeamsComponent } from '../all-teams/all-teams.component';
import { AllCavesComponent } from '../all-caves/all-caves.component';

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
              private killerSender: KillerHttpSenderService,
              private caveSender: CaveHttpSenderService,
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
    this.arraySender.getAllElements('?order_by=dragon_id&size=5&page=0').subscribe((data: Array<Dragon>) => {
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
        let er:HttpErrorResponse = error;
        console.log(`${er.error}\nstatus ${er.status}\n${er.message}\n${er.name}\n${er.type}`)
        alert(`${er.status}\n${er.error}`)
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

          this.dragonSender.updateDragon(newVal).subscribe({
            next: data => {
              this.loadDragons();
            },
            error: error => {
              let er:HttpErrorResponse = error;
              console.log(`${er.error}\nstatus ${er.status}\n${er.message}\n${er.name}\n${er.type}`)
              alert(`${er.status}\n${er.error}`)
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
        console.log(data);
        this.dragonSender.addDragon(dragon).subscribe({
          next: data => {
            this.loadDragons();
          },
          error: error => {
            let er:HttpErrorResponse = error;
            console.log(`${er.error}\nstatus ${er.status}\n${er.message}\n${er.name}\n${er.type}`)
            alert(`${er.status}\n${er.error}`)
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
    this.arraySender.getAllElements('?order_by=dragon_id&size=5&page=0').subscribe((data: Array<Dragon>) => {
      this.dragonList = data;
    });
  }

  getAgeSum() {
    this.dragonSender.getAgeSum().subscribe({
      next: data => {
        alert(data)
      },
      error: error => {
        let er:HttpErrorResponse = error;
        console.log(error)
        alert(`${er.status}\n${er.error}`)
      }
    })
  }

  getMinId() {
    this.dragonSender.getMinId().subscribe((data:Dragon) => {
      this.dragonList = [];
      this.dragonList.push(data);
    })
  }

  removeByCharacter() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(DeleteDragonsByCharComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(data => {
      if(data != undefined) {
        this.dragonSender.removeByCharacter(data).subscribe({
          next: data => {
            console.log(data);
            this.loadDragons();
          },
          error: error => {
            let er:HttpErrorResponse = error;
            console.log(`${er.error}\nstatus ${er.status}\n${er.message}\n${er.name}\n${er.type}`)
            alert(`${er.status}\n${er.error}`)
          }
        })
      }
    })
  }

  createCave() {
    this.caveSender.createCave().subscribe({
      next:data => {
        alert('Successful')
      },
      error:error => {
        let er:HttpErrorResponse = error;
        console.log(`${er.error}\nstatus ${er.status}\n${er.message}\n${er.name}\n${er.type}`)
        alert(`${er.status}\n${er.error}`)
      }
    })
  }

  getAllCaves() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    const dialogRef = this.dialog.open(AllCavesComponent, dialogConfig);
  }

  createKiller() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(AddKillerComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(data => {
      if(data !== undefined){
        const killer:Person = data;
        console.log(killer);
        this.killerSender.createKiller(killer).subscribe({
          next:data => {
            alert('Successful')
          },
          error:error => {
            let er:HttpErrorResponse = error;
            console.log(`${er.error}\nstatus ${er.status}\n${er.message}\n${er.name}\n${er.type}`)
            alert(`${er.status}\n${er.error}`)
          }
        })
      }
    })
  }

  createTeam() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(AddTeamComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(data => {
      if(data !== undefined) {
        const team:Team = data;
        if(team.teamId<0 || team.startCave<0 || team.teamSize<0) {
          alert(`400\nInvalid parameters`)
        }
        console.log(team.teamId);
        this.killerSender.createKillerTeam(team.teamId, team.teamName, team.teamSize, team.startCave).subscribe({
          next:data => {
            alert('Successful')
          },
          error:error => {
            let er:HttpErrorResponse = error;
            console.log(`${er.error}\nstatus ${er.status}\n${er.message}\n${er.name}\n${er.type}`)
            alert(`${er.status}\n${er.error}`)
          }
        });
      }
    })
  }

  sendTeamToCave() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(SendToCaveComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(data => {
      if(data !== undefined) {
        const move:Move = data;
        if(move.caveId<0 || move.teamId<0) {
          alert(`400\nInvalid parameters`)
        }
        console.log(`${move.caveId} ${move.teamId}`)
        this.killerSender.sendKillerToCave(move.teamId, move.caveId).subscribe({
          next:data => {
            alert('Successful')
          },
          error:error => {
            let er:HttpErrorResponse = error;
            console.log(`${er.error}\nstatus ${er.status}\n${er.message}\n${er.name}\n${er.type}`)
            alert(`${er.status}\n${er.error}`)
          }
        });
      }
    })
  }

  getAllTeams() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    const dialogRef = this.dialog.open(AllTeamsComponent, dialogConfig);
  }

}
