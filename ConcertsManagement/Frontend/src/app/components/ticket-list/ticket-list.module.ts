import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatListModule } from '@angular/material/list';
import { TicketListComponent } from '../ticket-list/ticket-list.component';
import { MatButtonModule } from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon'
import {MatExpansionModule} from '@angular/material/expansion';
import { MatInputModule } from '@angular/material/input';


import { FormsModule }   from '@angular/forms';



@NgModule({
  declarations: [TicketListComponent],
  imports: [
    CommonModule,
    MatListModule,
    MatButtonModule,
    MatIconModule,
    MatExpansionModule,
    MatInputModule,
    FormsModule
  ]
})
export class TicketListModule { }
