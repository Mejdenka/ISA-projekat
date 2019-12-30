INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja, confirmed) VALUES ('Pacijent', 'user', '$2a$10$e.g6dKlg/9jbUZFp4IcdnOAFJZbydpsge2ODaRY4b3uvDN9eNsjZK', 'Marko', 'Markovic', 'user@example.com', 1, '2017-10-01 21:58:58', '2000-01-01 18:57:58', 1);
INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja, confirmed) VALUES ('AdministratorKlinickogCentra', 'admin', '$2a$10$e.g6dKlg/9jbUZFp4IcdnOAFJZbydpsge2ODaRY4b3uvDN9eNsjZK', 'Nikola', 'Nikolic','admin@example.com', 1, '2017-10-01 18:57:58', '1997-10-01 18:57:58', 1);
INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja, confirmed) VALUES ('AdministratorKlinickogCentra', 'adminPero', '$2a$10$e.g6dKlg/9jbUZFp4IcdnOAFJZbydpsge2ODaRY4b3uvDN9eNsjZK', 'Pero', 'Peric','pero.peric@example.com', 1, '2017-10-01 18:57:58', '1997-10-01 18:57:58', 1);
INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja, confirmed) VALUES ('AdministratorKlinike', 'adminK', '$2a$10$e.g6dKlg/9jbUZFp4IcdnOAFJZbydpsge2ODaRY4b3uvDN9eNsjZK', 'Jovan', 'Jovanovic','adminKlinike@example.com', 1, '2017-10-01 18:57:58', '1997-10-01 18:57:58', 1);
INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja, confirmed, klinika_id, slobodan, na_godisnjem, ocena, obrisan) VALUES ('Lekar', 'lekar', '$2a$10$e.g6dKlg/9jbUZFp4IcdnOAFJZbydpsge2ODaRY4b3uvDN9eNsjZK', 'Jovana', 'Jovanovic','lekar@example.com', 1, '2017-10-01 18:57:58', '1997-10-01 18:57:58', 1, 1, 1, 0, 5, 0);
INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja, confirmed, klinika_id, slobodan, na_godisnjem, ocena, obrisan) VALUES ('Lekar', 'probaLekar', '$2a$10$e.g6dKlg/9jbUZFp4IcdnOAFJZbydpsge2ODaRY4b3uvDN9eNsjZK', 'Amelie', 'Lens','amelie@example.com', 1, '2017-10-01 18:57:58', '1997-10-01 18:57:58', 1, 1, 0, 0, 4, 0);

INSERT INTO klinika (naziv, lokacija, br_lekara, br_sala, opis, img_path) VALUES ('Klinika 1', 'Janka Veselinovica, 20', 10, 4, 'Prva klinika u centru.','images.icons/favicon.ico');

INSERT INTO sala (naziv, slobodna, rezervisana, obrisana, klinika_id) VALUES ('Sala 1', 1, 0, 0, 1);
INSERT INTO sala (naziv, slobodna, rezervisana, obrisana, klinika_id) VALUES ('Sala 2', 0, 0, 0, 1);
INSERT INTO sala (naziv, slobodna, rezervisana, obrisana, klinika_id) VALUES ('Sala 3', 1, 0, 0, 1);

INSERT INTO termin (pocetak, kraj, sala_id) VALUES ('2019-12-24 15:50:00', '2019-12-24 16:50:00', 1);
INSERT INTO termin (pocetak, kraj, sala_id) VALUES ('2019-12-25 12:50:00', '2019-12-25 13:20:00', 1);

INSERT INTO authority (name) VALUES ('ROLE_USER');
INSERT INTO authority (name) VALUES ('ROLE_PACIJENT');
INSERT INTO authority (name) VALUES ('ROLE_DOKTOR');
INSERT INTO authority (name) VALUES ('ROLE_MEDICINSKA_SESTRA');
INSERT INTO authority (name) VALUES ('ROLE_ADMIN_KLINIKE');
INSERT INTO authority (name) VALUES ('ROLE_ADMIN');

INSERT INTO korisnik_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO korisnik_authority (user_id, authority_id) VALUES (1, 2);
INSERT INTO korisnik_authority (user_id, authority_id) VALUES (3, 1);
INSERT INTO korisnik_authority (user_id, authority_id) VALUES (3, 6);
INSERT INTO korisnik_authority (user_id, authority_id) VALUES (2, 1);
INSERT INTO korisnik_authority (user_id, authority_id) VALUES (2, 6);
INSERT INTO korisnik_authority (user_id, authority_id) VALUES (4, 1);
INSERT INTO korisnik_authority (user_id, authority_id) VALUES (4, 5);
INSERT INTO korisnik_authority (user_id, authority_id) VALUES (5, 1);
INSERT INTO korisnik_authority (user_id, authority_id) VALUES (5, 3);
INSERT INTO korisnik_authority (user_id, authority_id) VALUES (6, 1);
INSERT INTO korisnik_authority (user_id, authority_id) VALUES (6, 3);

INSERT INTO admin_klinike_klinika (klinika_id, admin_id) VALUES (1, 4);
