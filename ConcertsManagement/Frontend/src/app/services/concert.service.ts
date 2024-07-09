import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConcertService {

  private apiUrl = 'http://localhost:8080'; 

  constructor(private http: HttpClient) { }

  getConcerts(): Observable<any> {
    return this.http.get(`${this.apiUrl}/concerts/all`);
  }

  getArtists(concertId: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/artists/${concertId}/artists`);
  }

  getArtistById(artistId: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/artists/${artistId}`);
  }

  getArtist(artistId: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/artists/${artistId}`);
  }

  addTicket(ticketData: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/tickets`, ticketData);
  }

  getTickets(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/participants/5/tickets`);
  }

  deleteTicket(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/tickets/${id}`);
  }

  // updateSeatNumber(ticketId: number, newSeatNumber: string): Observable<void> {
  //   const params = new HttpParams().set('seatNumber', newSeatNumber);
  //   return this.http.put<void>(`${this.apiUrl}/tickets/${ticketId}/seat-number`, null, { params });
  // }

  updateSeatNumber(ticketId: number, newSeatNumber: string): Observable<void> {
    const url = `${this.apiUrl}/tickets/${ticketId}`;
    const params = { newSeatNumber: newSeatNumber };
    return this.http.put<void>(url, null, { params });
  }

}

