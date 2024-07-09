package org.example.masproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.masproject.repository.ArtistRepository;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"recordlabel_id", "artist_id" })
})
public class ArtistInRecordLabel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recordlabel_id", nullable = false)
    @NotNull
    private RecordLabel recordlabel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id", nullable = false)
    @NotNull
    private Artist artist;

    @NotNull
    private LocalDate joinDate;

    private LocalDate endDate;

    @Min(5)
    private int commission;

}
