package org.example.masproject.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StandardParticipant extends Participant{

    @NotEmpty
    private boolean isStanding;
    public VIPParticipant changeParticipantType(boolean isWithFood) {
            return VIPParticipant.builder()
                    .birthDate(this.getBirthDate())
                    .email(this.getEmail())
                    .phoneNumber(this.getPhoneNumber())
                    .isWithFood(isWithFood)
                    .build();
    }
}
