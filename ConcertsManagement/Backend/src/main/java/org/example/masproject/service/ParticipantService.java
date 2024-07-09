package org.example.masproject.service;

import org.example.masproject.model.Participant;
import org.example.masproject.model.StandardParticipant;
import org.example.masproject.model.Ticket;
import org.example.masproject.model.VIPParticipant;
import org.example.masproject.repository.ConcertRepository;
import org.example.masproject.repository.ParticipantRepository;
import org.example.masproject.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ListResourceBundle;
import java.util.Optional;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private TicketService ticketService;
    private TicketRepository ticketRepository;

    @Autowired
    private ConcertRepository concertRepository;

    public Optional<Participant> getParticipantById(Long id) {
        return participantRepository.findById(id);
    }


    public StandardParticipant changeParticipantTypeToStandard(Long vipParticipantId, boolean isStanding) {
        Optional<Participant> vipParticipant = participantRepository.findById(vipParticipantId);
        if(vipParticipant.isEmpty()){
            throw new RuntimeException("Participant not found");
        }

        VIPParticipant vp = (VIPParticipant) vipParticipant.get();
        StandardParticipant standardParticipant = vp.changeParticipantType(isStanding);

        standardParticipant = participantRepository.save(standardParticipant);

        participantRepository.delete(vp);

        return standardParticipant;
    }

    public VIPParticipant changeParticipantToVIP(Long standardParticipantId, boolean isWithFood) {
        Optional<Participant> standardParticipant = participantRepository.findById(standardParticipantId);
        if(standardParticipant.isEmpty()){
            throw new RuntimeException("Participant not found");
        }

        StandardParticipant sp = (StandardParticipant) standardParticipant.get();
        VIPParticipant vipParticipant = sp.changeParticipantType(isWithFood);

        vipParticipant = participantRepository.save(vipParticipant);

        participantRepository.delete(sp);

        return vipParticipant;
    }





}
