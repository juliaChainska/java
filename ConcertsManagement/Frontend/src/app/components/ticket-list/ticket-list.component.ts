import { Component, OnInit } from '@angular/core';
import { ConcertService } from '../../services/concert.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-ticket-list',
  templateUrl: './ticket-list.component.html',
  styleUrl: './ticket-list.component.css'
})
export class TicketListComponent implements OnInit{

  tickets: any[] = [];
  editedTicketId: number | null = null;
  newSeatNumber: string = '';
  regex = /^[A-Za-z]\d{1,3}$/;
  seatPattern = "^[A-Za-z]\d{1,3}$";


  constructor(private concertService: ConcertService, private router: Router) { }


  ngOnInit(): void {
    console.log('TicketComponent loaded'); // Logowanie załadowania komponentu
    this.loadTickets();
  }


  loadTickets(): void {
    this.concertService.getTickets().subscribe(data => {
      console.log('Tickets loaded:', data); // Logowanie pobranych ticketow
      this.tickets = data;
    });
  }
  
  deleteTicket(id: number) {
    this.concertService.deleteTicket(id).subscribe(() => {
      this.loadTickets();
    });
  }

  trackByTicket(index: number, ticket: any): number {
    return ticket.ticketId;
}
editSeatNumber(ticketId: number, seatNumber: string): void {
  this.editedTicketId = ticketId;
  this.newSeatNumber = seatNumber;
}

saveSeatNumber(ticketId: number): void {
  this.concertService.updateSeatNumber(ticketId, this.newSeatNumber).subscribe(response => {
    this.loadTickets(); 
    this.editedTicketId = null;
    this.newSeatNumber = '';
  });
}


cancelEdit(): void {
  this.editedTicketId = null;
  this.newSeatNumber = '';
}


  navigateToHome(): void {
    this.router.navigate(['/concerts']);
  }

  


}
