package org.example.masproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Participant extends Person{

    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;

    @NotNull(message = "phone number is mandatory")
    @Min(0)
    private int phoneNumber;

    @NotNull(message = "birth date is mandatory")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ticket> tickets = new HashSet<>();

    @Override
    public String displayDetails() {
        return super.displayDetails() + "Email: " + email + " - Phone Number: " + phoneNumber;
    }

    public Long getId() {
        return getId();
    }
}
