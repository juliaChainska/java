import { Component, OnInit } from '@angular/core';
import { ConcertService } from '../../services/concert.service';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';


@Component({
  selector: 'app-concerts',
  templateUrl: './concerts.component.html',
  styleUrl: './concerts.component.css'
})
export class ConcertsComponent implements OnInit{

  concerts: any[] = [];
  selectedConcert: any = null;
  saelectedArtist: any = null;
  artists: any[] = [];

  participantId = 5;
  selectedConcertId = null;

  displayedColumns: string[] = ['title', 'location', 'date', 'price'];

  constructor(private concertService: ConcertService, private router: Router, private formBuilder: FormBuilder) { }

  profileForm = this.formBuilder.group({
    seatNumber:['', Validators.required, Validators.pattern(/^[A-Za-z]\d{1,3}$/)]
  })

  ngOnInit(): void {
    console.log('ConcertsComponent loaded'); // Logowanie załadowania komponentu
    this.loadConcerts();
  }

  loadConcerts(): void {
    this.concertService.getConcerts().subscribe(data => {
      console.log('Concerts loaded:', data); // Logowanie pobranych koncertów
      this.concerts = data;
    });
  }

  selectConcert(concert: any): void {
    console.log('Concert selected:', concert); // Logowanie wybranego koncertu
    this.selectedConcert = concert;
    this.selectedConcertId = concert.id;
    this.loadArtists(concert.id);
  }

  loadArtists(concertId: string): void {
    this.concertService.getArtists(concertId).subscribe(data => {
      console.log('Artists loaded:', data); // Logowanie pobranych artystów
      this.artists = data;
    });
  }

  onConcertClick(concertId: string): void {
    this.router.navigate(['/artists', concertId]);
  }

  onArtistClick(artistNick: string): void {
    this.router.navigate(['/artists', artistNick]);
  }

  selectArtist(artist: any): void {
    console.log('Artist selected:', artist); 
    this.saelectedArtist = artist;
    this.navigateToArtistDetail(artist.id);
  }

  navigateToArtistDetail(artistId: any): void {
    this.router.navigate(['/artist-detail', artistId]);
  }

  addTicket(formValues: any) {
    const ticketData = {
      // price: formValues.price,
      price: 60.21,
      seatNumber: formValues.seatNumber,
      concertId: this.selectedConcertId,
      // participantId: formValues.participantId
      participantId: this.participantId
    };

    console.log(this.selectedConcertId);

    this.concertService.addTicket(ticketData).subscribe(response => {
      alert('Bilet został pomyślnie zakupiony!');
    }, error => {
      console.error('Wystąpił błąd podczas zakupu biletu:', error);
      alert('Nie udało się kupić biletu. Spróbuj ponownie później.');
    });
  }


  navigateToTicketList(): void {
    this.router.navigate(['/ticket-list']);
  }

}
