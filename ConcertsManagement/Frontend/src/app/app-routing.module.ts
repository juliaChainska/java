import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConcertsComponent } from './components/concerts/concerts.component';
import { ArtistDetailComponent } from './components/artist-detail/artist-detail.component'; 
import { TicketListComponent } from './components/ticket-list/ticket-list.component';


const routes: Routes = [
  { path: 'concerts', component: ConcertsComponent },
  { path: '', redirectTo: '/concerts', pathMatch: 'full' },
  { path: 'ticket-list', component: TicketListComponent },
  { path: 'concerts', component: ConcertsComponent },
  { path: 'artist-detail/:id', component: ArtistDetailComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
