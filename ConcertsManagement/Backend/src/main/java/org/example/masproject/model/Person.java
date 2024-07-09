package org.example.masproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "name is mandatory")
    private String name;

    @NotBlank(message = "lasname is mandatory")
    private String lastname;

    @NotBlank(message = "pesel is mandatory")
    @Size(min = 11, max = 11, message = "PESEL must be exactly 11 characters")
    @Column(nullable = false, unique = true)
    private String pesel;

    @NotBlank(message = "Nationality is mandatory")
    private String nationality;

    public Person() {}

    public String displayDetails(){
        return "Name: " + name + " - LastName: " + lastname + " - Nationality: " + nationality;

    };


}
