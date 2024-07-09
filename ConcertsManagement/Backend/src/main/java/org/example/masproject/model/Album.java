package org.example.masproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @NotBlank(message = "Title is mandatory")
    private String title; //wymagany

    @NotNull(message = "releaseYear is mandatoy)")
    private int releaseYear;


    private Double price; //opcjonalny

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Song> songs; //zlozony


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id", nullable = false, updatable = false)
    private Artist artist;


    public int howOldIsAlbum(){
        return LocalDate.now().getYear() - releaseYear;
    }









}
