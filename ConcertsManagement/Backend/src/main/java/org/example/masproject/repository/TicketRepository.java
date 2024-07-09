package org.example.masproject.repository;

import org.example.masproject.dtos.TicketResponseDTO;
import org.example.masproject.model.Concert;
import org.example.masproject.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT t FROM Ticket t " +
            "JOIN FETCH t.concert c " +
            "JOIN FETCH t.participant p " +
            "WHERE p.id = :id")
    List<Ticket> findAllByParticipantId(Long id);


}
