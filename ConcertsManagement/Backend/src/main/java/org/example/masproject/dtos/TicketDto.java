package org.example.masproject.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TicketDto {
    long concertId;
    long participantId;
    String seatNumber;
    double price;
}
