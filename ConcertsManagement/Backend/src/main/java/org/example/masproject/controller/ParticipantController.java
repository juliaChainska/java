package org.example.masproject.controller;

import org.example.masproject.dtos.TicketDto;
import org.example.masproject.dtos.TicketResponseDTO;
import org.example.masproject.model.Ticket;
import org.example.masproject.service.ParticipantService;
import org.example.masproject.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/participants")
public class ParticipantController {
    @Autowired
    private ParticipantService participantService;

    @Autowired
    private TicketService ticketService;

    @GetMapping("/{id}/tickets")
    public List<TicketResponseDTO> getAllParticipantTickets(@PathVariable Long id) {
        return ticketService.getParticipantsTickets(id);
    }



}
