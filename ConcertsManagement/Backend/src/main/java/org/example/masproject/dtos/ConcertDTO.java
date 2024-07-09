package org.example.masproject.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.example.masproject.model.Artist;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
public class ConcertDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "date")
    private LocalDate date;

    @JsonProperty(value = "location")
    private String location;

    @JsonProperty(value = "artists")
    private List<ArtistDTO> artists;

    @JsonProperty(value = "price")
    private double price;


}
