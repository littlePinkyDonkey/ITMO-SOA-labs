import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddDragonComponent } from './add-dragon/add-dragon.component';
import { ContentComponent } from './content/content.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule }   from '@angular/common/http';

import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from "@angular/material/dialog";
import { MatInput, MatInputModule } from '@angular/material/input';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'

import { ArrayHttpSenderService } from './services/array-http-sender.service';
import { DragonHttpSenderService } from './services/dragon/dragon-http-sender.service';

@NgModule({
  declarations: [
    AppComponent,
    ContentComponent,
    AddDragonComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatTableModule,
    MatPaginatorModule,
    MatButtonModule,
    MatDialogModule,
    MatInputModule,
    BrowserAnimationsModule
  ],
  providers: [ArrayHttpSenderService, DragonHttpSenderService],
  bootstrap: [AppComponent],
  entryComponents: [AddDragonComponent]
})
export class AppModule { }
