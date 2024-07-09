package org.example.masproject.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.example.masproject.model.Album;

import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
public class ArtistResponseDTO {

    @JsonProperty(value = "nick")
    private String nick;

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "types")
    private String type;

    @JsonProperty(value = "albums")
    private Set<Album> albums;
}
