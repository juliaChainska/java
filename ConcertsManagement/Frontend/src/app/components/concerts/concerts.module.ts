import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { ConcertsComponent } from './concerts.component';
import { MatListModule } from '@angular/material/list';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

import { FormsModule }   from '@angular/forms';


@NgModule({
  declarations: [ConcertsComponent],
  imports: [
    CommonModule,
    MatTableModule,
    MatListModule,
    MatInputModule,
    FormsModule,
    MatButtonModule
  ]
})
export class ConcertsModule { }
