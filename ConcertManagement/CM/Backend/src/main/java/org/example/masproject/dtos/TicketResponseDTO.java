package org.example.masproject.dtos;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TicketResponseDTO {
    long ticketId;
    long concertId;
    long participantId;
    String seatNumber;
    double price;
    String concertName;
}
