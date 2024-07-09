package org.example.masproject.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

@Table(name = "tickets", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"concert_id", "seatNumber"}, name = "unique_seat_per_concert")
})
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id", nullable = false)
    @JsonIgnore
    private Participant participant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_id")
    @JsonIgnore
    private Concert concert;

    @NotNull
    @Positive(message = "Price must be greater than 0")
    private double price;

    @NotBlank
    private String seatNumber;

}
