package org.example.masproject.service;


import lombok.RequiredArgsConstructor;
import org.example.masproject.dtos.TicketDto;
import org.example.masproject.dtos.TicketResponseDTO;
import org.example.masproject.model.Concert;
import org.example.masproject.model.Participant;
import org.example.masproject.model.Ticket;
import org.example.masproject.repository.ConcertRepository;
import org.example.masproject.repository.ParticipantRepository;
import org.example.masproject.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ConcertRepository concertRepository;
    private final ParticipantRepository participantRepository;

    public long createTicket(TicketDto ticketDto) {
        Optional<Concert> concert = concertRepository.findById(ticketDto.getConcertId());
        if (concert.isEmpty()) {
            throw new RuntimeException();
        }

        Optional<Participant> participant = participantRepository.findById(ticketDto.getParticipantId());
        if (participant.isEmpty()) {
            throw new RuntimeException();
        }

        if(!isTicketFree(ticketDto.getSeatNumber(), concert.get())){
            throw new RuntimeException("Miejsce jest już zajęte");
        }

        Ticket ticket = Ticket.builder()
                .price(ticketDto.getPrice())
                .seatNumber(ticketDto.getSeatNumber())
                .concert(concert.get())
                .participant(participant.get())
                .build();

        try{
            ticketRepository.save(ticket);
        } catch (Exception e) {
            throw new RuntimeException("Miejsce jest juz zajete");
        }


        return ticket.getId();
    }


    public boolean isTicketFree(String seatNumber, Concert concert){
        Set<Ticket> tickets = concert.getTickets();
        for(Ticket t : tickets){
            if(t.getSeatNumber().equals(seatNumber)){
                return false;
            }
        }
        return true;
    }

    public Optional<Ticket> findById(Long id) {
        return ticketRepository.findById(id);
    }

    public List<TicketResponseDTO> getParticipantsTickets(Long id){
        List<Ticket> tickets = ticketRepository.findAllByParticipantId(id);
        List<TicketResponseDTO> ticketDto = new ArrayList<>();

        for(Ticket t : tickets) {
            ticketDto.add(TicketResponseDTO.builder()
                    .concertId(t.getConcert().getId())
                    .price(t.getConcert().getPrice())
                    .seatNumber(t.getSeatNumber())
                    .concertName(t.getConcert().getTitle())
                    .ticketId(t.getId())
                    .build());
        }

        return ticketDto;
    }


    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    public void updateSeatNumber(Long ticketId, String newSeatNumber) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isEmpty()) {
            throw new RuntimeException("Ticket not found");
        }

        Ticket ticket = optionalTicket.get();
        ticket.setSeatNumber(newSeatNumber);
        ticketRepository.save(ticket);
    }

}
