package org.example.masproject.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class VIPParticipant extends Participant {

    private boolean isWithFood;

    public StandardParticipant changeParticipantType(boolean isStanding) {
        return StandardParticipant.builder()
                .email(this.getEmail())
                .phoneNumber(this.getPhoneNumber())
                .birthDate(this.getBirthDate())
                .isStanding(isStanding)
                .build();
    }






}
