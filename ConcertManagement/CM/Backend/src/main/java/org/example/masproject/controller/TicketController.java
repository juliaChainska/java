package org.example.masproject.controller;

import jakarta.websocket.server.PathParam;
import org.example.masproject.dtos.TicketDto;
import org.example.masproject.model.Artist;
import org.example.masproject.model.Concert;
import org.example.masproject.model.Participant;
import org.example.masproject.model.Ticket;
import org.example.masproject.service.ConcertService;
import org.example.masproject.service.ParticipantService;
import org.example.masproject.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private ConcertService concertService;
    @Autowired
    private ParticipantService participantService;

    @PostMapping
    public ResponseEntity<Long> createTicket(@RequestBody TicketDto ticketDto) {
        return new ResponseEntity<>(ticketService.createTicket(ticketDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
    }

    @PutMapping("/{ticketId}")
    public ResponseEntity<Void> updateSeatNumber(@PathVariable Long ticketId, @RequestParam String newSeatNumber) {
        ticketService.updateSeatNumber(ticketId, newSeatNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
