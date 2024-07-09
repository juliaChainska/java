insert into concert (id, location, title, date, tour_id) values
                                                             (1, 'Krakow', 'Krakow Late Night Concert 1', CURRENT_DATE, 1),
                                                             (2, 'Los Angeles', 'Eras Tour', CURRENT_DATE, 1),
                                                             (3, 'Warsaw', 'Hit', '2025-06-05', 1);

-- Wstawianie danych do tabeli artist
INSERT INTO artist (id, nick) VALUES (1, 'Travis Scott');
INSERT INTO artist (id, nick) VALUES (2, 'Barry James');
--
-- -- Wstawianie danych do tabeli concert
-- INSERT INTO concert (id, location, title, date, tour_id) VALUES (4, 'New York', 'Spring Boot Concert', '2024-08-20', 1);
-- INSERT INTO concert (id, location, title, date, tour_id) VALUES (5, 'Los Angeles', 'Hibernate Concert', '2024-10-01', 2);
--
-- -- Wstawianie danych do tabeli concert_artist (relacja wiele do wielu)
-- INSERT INTO concert_artist (concert_id, artist_id) VALUES (4, 1);
-- INSERT INTO concert_artist (concert_id, artist_id) VALUES (4, 2);
-- INSERT INTO concert_artist (concert_id, artist_id) VALUES (5, 1);

-- -- Wstawianie danych do tabeli tour
-- INSERT INTO tour (concert_id, artist_id) VALUES (5, 1);
