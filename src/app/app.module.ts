import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddDragonComponent } from './add-dragon/add-dragon.component';
import { ContentComponent } from './content/content.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule }   from '@angular/common/http';

import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from "@angular/material/dialog";
import { MatInput, MatInputModule } from '@angular/material/input';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { MatSelectModule } from '@angular/material/select';

import { ArrayHttpSenderService } from './services/array-http-sender.service';
import { DragonHttpSenderService } from './services/dragon/dragon-http-sender.service';
import { EditDragonComponent } from './edit-dragon/edit-dragon.component';
import { SetParametersComponent } from './set-parameters/set-parameters.component';
import { DeleteDragonsByCharComponent } from './delete-dragons-by-char/delete-dragons-by-char.component';
import { AddKillerComponent } from './add-killer/add-killer.component';
import { AddTeamComponent } from './add-team/add-team.component';
import { SendToCaveComponent } from './send-to-cave/send-to-cave.component';
import { AllTeamsComponent } from './all-teams/all-teams.component';
import { AllCavesComponent } from './all-caves/all-caves.component';

@NgModule({
  declarations: [
    AppComponent,
    ContentComponent,
    AddDragonComponent,
    EditDragonComponent,
    SetParametersComponent,
    DeleteDragonsByCharComponent,
    AddKillerComponent,
    AddTeamComponent,
    SendToCaveComponent,
    AllTeamsComponent,
    AllCavesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatTableModule,
    ReactiveFormsModule,
    MatPaginatorModule,
    MatButtonModule,
    MatDialogModule,
    MatInputModule,
    BrowserAnimationsModule,
    MatSelectModule
  ],
  providers: [ArrayHttpSenderService, DragonHttpSenderService],
  bootstrap: [AppComponent],
  entryComponents: [AddDragonComponent]
})
export class AppModule { }
