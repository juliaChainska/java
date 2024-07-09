package org.example.masproject.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.example.masproject.model.ArtistType;

import javax.management.relation.Role;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
public class ArtistDTO {

    @JsonProperty(value = "nick")
    private String nick;

    @JsonProperty(value = "artistType")
    private Set<ArtistType> artistType;

}
