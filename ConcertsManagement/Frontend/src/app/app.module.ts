import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ConcertsComponent } from './components/concerts/concerts.component';
import { ConcertsModule } from './components/concerts/concerts.module';
import { TicketListModule } from './components/ticket-list/ticket-list.module';
import { ConcertService } from './services/concert.service';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TicketListComponent } from './components/ticket-list/ticket-list.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';



@NgModule({
  declarations: [
    AppComponent,
    // ConcertsComponent,
    // TicketListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    ConcertsModule,
    TicketListModule

  ],
  providers: [
    provideClientHydration(),
    ConcertService,
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
