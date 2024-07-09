import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ConcertService } from '../../services/concert.service'

@Component({
  selector: 'app-artist-detail',
  templateUrl: './artist-detail.component.html',
  styleUrl: './artist-detail.component.css'
})
export class ArtistDetailComponent implements OnInit {
  artist: any;
  artistNick: string;

  constructor(private route: ActivatedRoute, private concertService: ConcertService) {
    const nick = this.route.snapshot.paramMap.get('artistNick');
    if (nick === null) {
      throw new Error('Artist nick cannot be null');
    }
    this.artistNick = nick;
  }

  ngOnInit(): void {


    this.route.paramMap.subscribe(params => {
      const artistNick = params.get('nick');
      if (artistNick) {
        this.getArtistDetails(artistNick);
      }
    });
  }

  getArtistDetails(artistNick: string): void {
    this.concertService.getArtist(artistNick).subscribe(data => {
      this.artist = data;
    });
  }

  
}
