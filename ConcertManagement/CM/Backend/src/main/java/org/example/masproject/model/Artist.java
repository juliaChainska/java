package org.example.masproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
public class Artist extends Person{

    @NotBlank(message = "Name is mandatory")
    @Column(unique = true, nullable = false)
    private String nick;

    @ElementCollection(targetClass = ArtistType.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING) // zapisuje nazwy enumow jako stringi w bazie danych
    @CollectionTable(name = "artist_types", joinColumns = @JoinColumn(name = "artist_id"))
    @Column(name = "type")
    private Set<ArtistType> types;

    @ManyToMany(mappedBy = "artists", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<Concert> concerts;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Album> albums = new HashSet<>();

    @OneToMany(mappedBy = "artist", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<ArtistInRecordLabel> agreements;


    private String vocalType; // only for vocalist

    private String instrument; //only for instrumentalist;

    public Artist(String nick, Set<ArtistType> types, Set<Concert> concerts, Set<Album> albums, Set<ArtistInRecordLabel> agreements, String vocalType, String instrument) {
        this.nick = nick;
        this.types = types;
        this.concerts = concerts;
        this.albums = albums;
        this.agreements = agreements;

        if(types.contains(ArtistType.Vocalist)) {
            this.vocalType = vocalType;
        }

        if(types.contains(ArtistType.Instrumentalist)) {
            this.instrument = instrument;
        }
    }

    @Override
    public String displayDetails() {
        return super.displayDetails() + "Nick: " + nick + " - Type: " + types.toString();

    }

}
