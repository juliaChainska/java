package org.example.masproject;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.masproject.model.*;
import org.example.masproject.repository.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.cglib.core.Local;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {


    AlbumRepository albumRepository;
    ArtistRepository artistRepository;
    ConcertRepository concertRepository;
    TourRepository tourRepository;
    RecordLabelRepository recordLabelRepository;
    ArtistInRecordLabelRepository artistInRecordLabelRepository;
    ParticipantRepository participantRepository;

    Set<ArtistType> both = Set.of(ArtistType.Vocalist, ArtistType.Instrumentalist);
    Set<ArtistType> instrumentalist = Set.of(ArtistType.Instrumentalist);
    Set<ArtistType> vocalist = Set.of(ArtistType.Vocalist);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        RecordLabel recordLabel1 = RecordLabel.builder()
                .name("ABC")
                .build();

        RecordLabel recordLabel2 = RecordLabel.builder()
                .name("you")
                .build();


        if (recordLabelRepository.count() == 0) {
            recordLabelRepository.saveAll(
                    List.of(recordLabel1, recordLabel2));
        }

        Artist artist1 = Artist.builder()
                .name("Barry")
                .lastname("Jameson")
                .nationality("Peru")
                .types(instrumentalist)
                .pesel("12345678910")
                .nick("Barry James")
                .build();

        Artist artist2 = Artist.builder()
                .name("Travis")
                .lastname("Scotland")
                .nationality("USA")
                .types(vocalist)
                .pesel("01020304050")
                .nick("Travis Scott")
                .build();

        Artist artist3 = Artist.builder()
                .name("Tomasz")
                .lastname("Scot")
                .nationality("Poland")
                .types(vocalist)
                .pesel("11020304050")
                .nick("Tomek Skok")
                .build();

        Artist artist4 = Artist.builder()
                .name("Alan")
                .lastname("W")
                .nationality("USA")
                .types(both)
                .pesel("01020323050")
                .nick("AlanW")
                .build();

        if (artistRepository.count() == 0) {
            artistRepository.saveAll(
                    List.of(artist1, artist2, artist3, artist4));
        }

        ArtistInRecordLabel airl1 = ArtistInRecordLabel.builder()
                .artist(artist1)
                .recordlabel(recordLabel1)
                .joinDate(LocalDate.of(2020, 1, 1))
                .endDate(LocalDate.of(2022, 12, 31))
                .commission(15)
                .build();

        ArtistInRecordLabel airl2 = ArtistInRecordLabel.builder()
                .artist(artist2)
                .recordlabel(recordLabel2)
                .joinDate(LocalDate.of(2021, 2, 1))
                .commission(20)
                .build();

        if (artistInRecordLabelRepository.count() == 0) {
            artistInRecordLabelRepository.saveAll(
                    List.of(airl1, airl2));
        }

        Album album1 = Album.builder()
                .title("Utopia")
                .releaseYear(2020)
                .price(69.99)
                .artist(artist2)
                .build();

        Album album2 = Album.builder()
                .title("JazzAndBlues")
                .releaseYear(2021)
                .price(80.99)
                .artist(artist1)
                .build();

        Album album3 = Album.builder()
                .title("a")
                .releaseYear(2022)
                .price(92.99)
                .artist(artist2)
                .build();

        if (albumRepository.count() == 0) {
            albumRepository.saveAll(
                    List.of(album1, album2, album3));
        }


        Tour tour1 = Tour.builder()
                .title("First World Tour")
                .build();

        Tour tour2 = Tour.builder()
                .title("All Around Tour")
                .build();

        if (tourRepository.count() == 0) {
            tourRepository.saveAll(
                    List.of(tour1, tour2));
        }

        Set<Artist> concert1Artists = new HashSet<>();
        concert1Artists.add(artist1);
        concert1Artists.add(artist2);
        concert1Artists.add(artist3);

        Set<Artist> concert2Artists = new HashSet<>();
        concert2Artists.add(artist1);
        concert2Artists.add(artist4);

        Set<Artist> concert3Artists = new HashSet<>();
        concert3Artists.add(artist2);
        concert3Artists.add(artist1);
        concert3Artists.add(artist4);


        Concert concert1 = Concert.builder()
                .location("New York")
                .title("Spring Boot Concert")
                .date(LocalDate.of(2024, 6, 15))
                .tour(tour1)
                .artists(concert1Artists)
                .price(120.00)
                .build();

        Concert concert2 = Concert.builder()
                .location("Los Angeles")
                .title("HibernateConcert")
                .date(LocalDate.of(2024, 8, 20))
                .tour(tour2)
                .artists(concert2Artists)
                .price(68.0)
                .build();

        Concert concert3 = Concert.builder()
                .location("Warsaw")
                .title("AAAAA")
                .date(LocalDate.of(2025, 1, 1))
                .tour(tour2)
                .artists(concert3Artists)
                .price(100.00)
                .build();

        if (concertRepository.count() == 0) {
            concertRepository.saveAll(
                    List.of(concert1, concert2, concert3));
        }

        List<Song> songList = new ArrayList<>();

        Participant p1 = Participant.builder()
                .email("xxassd@g.com")
                .birthDate(LocalDate.now())
                .phoneNumber(9999)
                .lastname("XXXXXX")
                .pesel("11111111111")
                .name("YYYYY")
                .nationality("A")
                .build();
        if (participantRepository.count() == 0) {
            participantRepository.save(p1);
        }
    }
    private void initializeData() {
    }
}