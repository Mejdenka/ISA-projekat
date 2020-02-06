INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja, confirmed, klinika_pacijenta_id, jbo, karton_id, obrisan) VALUES ('Pacijent', 'user', '$2a$10$e.g6dKlg/9jbUZFp4IcdnOAFJZbydpsge2ODaRY4b3uvDN9eNsjZK', 'Marko', 'Markovic', 'user@example.com', 1, '2017-10-01 21:58:58', '2000-01-01 18:57:58', 1, 1, '1512997186555', 1, 0);
INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja, confirmed, obrisan) VALUES ('AdministratorKlinickogCentra', 'admin', '$2a$10$e.g6dKlg/9jbUZFp4IcdnOAFJZbydpsge2ODaRY4b3uvDN9eNsjZK', 'Nikola', 'Nikolic','admin@example.com', 1, '2017-10-01 18:57:58', '1997-10-01 18:57:58', 1, 0);
INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja, confirmed, obrisan) VALUES ('AdministratorKlinickogCentra', 'adminPero', '$2a$10$e.g6dKlg/9jbUZFp4IcdnOAFJZbydpsge2ODaRY4b3uvDN9eNsjZK', 'Pero', 'Peric','pero.peric@example.com', 1, '2017-10-01 18:57:58', '1997-10-01 18:57:58', 1, 0);
INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja, confirmed, obrisan) VALUES ('AdministratorKlinike', 'adminK', '$2a$10$e.g6dKlg/9jbUZFp4IcdnOAFJZbydpsge2ODaRY4b3uvDN9eNsjZK', 'Jovan', 'Jovanovic','adminKlinike@example.com', 1, '2017-10-01 18:57:58', '1997-10-01 18:57:58', 1, 0);
INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja, confirmed, klinika_lekara_id, slobodan, na_godisnjem, ocena, obrisan, radno_vreme) VALUES ('Lekar', 'lekar','$2a$10$e.g6dKlg/9jbUZFp4IcdnOAFJZbydpsge2ODaRY4b3uvDN9eNsjZK', 'Jovana', 'Jovanovic','lekar@example.com', 1,'2017-10-01 18:57:58', '1997-10-01 18:57:58', 1, 1, 1, 0, 5, 0, '08:00-16:00');
INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja, confirmed, klinika_lekara_id, slobodan, na_godisnjem, ocena, obrisan, radno_vreme) VALUES ('Lekar', 'probaLekar','$2a$10$e.g6dKlg/9jbUZFp4IcdnOAFJZbydpsge2ODaRY4b3uvDN9eNsjZK', 'Amelie', 'Lens','amelie@example.com', 1, '2017-10-01 18:57:58','1997-10-01 18:57:58', 1, 1, 0, 0, 4, 0, '08:30-16:30');
INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja, confirmed, klinika_med_sestre_id, obrisan) VALUES('MedicinskaSestra', 'sestra', '$2a$10$e.g6dKlg/9jbUZFp4IcdnOAFJZbydpsge2ODaRY4b3uvDN9eNsjZK', 'Svetlana', 'Micic','sestra@example.com', 1,'2017-10-01 18:57:58', '1997-10-01 18:57:58', 1, 1, 0);
INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja, confirmed, klinika_pacijenta_id, jbo, karton_id, obrisan) VALUES ('Pacijent', 'pacijent', '$2a$10$e.g6dKlg/9jbUZFp4IcdnOAFJZbydpsge2ODaRY4b3uvDN9eNsjZK', 'Marina', 'Simic', 'userka@example.com', 1, '2017-10-01 21:58:58', '2000-01-01 18:57:58', 1, 1, '1512997186666', 2, 0);

INSERT INTO klinika (naziv, lokacija, opis, prosecna_ocena) VALUES ('Klinika 1', 'Janka Veselinovica, 20', 'Prva klinika u centru.', 4.3);
INSERT INTO klinika (naziv, lokacija, opis, prosecna_ocena) VALUES ('Klinika 2', 'Novi Sad', 'Druga klinika u centru.', 2);

INSERT INTO sala (naziv, broj, slobodna, rezervisana, obrisana, klinika_sale_id) VALUES ('Sala 1', 1, 1, 1, 0, 1);
INSERT INTO sala (naziv,  broj, slobodna, rezervisana, obrisana, klinika_sale_id) VALUES ('Sala 2', 2, 0, 0, 0, 1);
INSERT INTO sala (naziv,  broj, slobodna, rezervisana, obrisana, klinika_sale_id) VALUES ('Sala 3', 3, 1, 0, 0, 1);

INSERT INTO tip_pregleda (naziv, cena, klinika_id, obrisan) VALUES ('Pregled glave', 100, 1, 0);
INSERT INTO tip_pregleda (naziv, cena, klinika_id, obrisan) VALUES ('Pregled srca', 200, 1, 0);
INSERT INTO tip_pregleda (naziv, cena, klinika_id, obrisan) VALUES ('Pregled srca', 200, 2, 0);

INSERT INTO termin (pocetak, kraj, rezervisan, klinika_termina_id, obrisan, pacijent_id) VALUES ('2020-02-12 15:50:00', '2020-02-12 16:50:00', 1, 1, 0, 1);
INSERT INTO termin (pocetak, kraj, rezervisan, klinika_termina_id, obrisan, pacijent_id) VALUES ('2020-02-13 12:50:00', '2020-02-13 13:20:00', 1, 1, 0, 8);
INSERT INTO termin (pocetak, kraj, rezervisan, klinika_termina_id, obrisan, pacijent_id) VALUES ('2020-02-14 10:30:00', '2020-02-14 16:00:00', 1, 1, 0, 8);
INSERT INTO termin (pocetak, kraj, rezervisan, klinika_termina_id, obrisan, pacijent_id) VALUES ('2020-02-10 10:30:00', '2020-02-10 16:00:00', 1, 1, 0, 1);

INSERT INTO godisnji_odsustvo_termin (pocetak, kraj, godisnji, odsustvo, obrisan, odobren, lekargo_id, lekar_ods_id, med_sestra_go_id, med_sestra_ods_id)
VALUES ('2020-02-14 10:30:00', '2020-02-14 16:00:00', 0, 1, 0, 0, null, null, null, 7);
INSERT INTO godisnji_odsustvo_termin (godisnji, odsustvo, pocetak, kraj, obrisan, lekargo_id, lekar_ods_id, odobren, med_sestra_go_id, med_sestra_ods_id) VALUES
 (1, 0, '2020-02-14 01:01:00', '2020-02-22 11:11:00', 0, 5, null, 0, null, null);
INSERT INTO pregled (lekar_id, sala_id, klinika_pregleda_id, pacijent_id, termin_id, tip_pregleda_id, obavljen) VALUES (5, 1, 1, 1, 2, 2, 1);
INSERT INTO pregled (lekar_id, sala_id, klinika_pregleda_id, pacijent_id, termin_id, tip_pregleda_id, obavljen) VALUES (5, 1, 1, 1, 1, 1, 0);

INSERT INTO pregled (lekar_id, sala_id, klinika_pregleda_id, pacijent_id, termin_id, tip_pregleda_id, obavljen, obrisan) VALUES (5, 3, 1, 1, 2, 2, 0, 0);
INSERT INTO pregled (lekar_id, sala_id, klinika_pregleda_id, pacijent_id, termin_id, tip_pregleda_id, obavljen, obrisan) VALUES (5, 2, 1, 8, 1, 1, 1, 0);
INSERT INTO pregled (lekar_id, sala_id, klinika_pregleda_id, pacijent_id, termin_id, tip_pregleda_id, obavljen, obrisan) VALUES (5, 1, 1, 8, 1, 1, 0, 0);
INSERT INTO pregled (lekar_id, sala_id, klinika_pregleda_id, pacijent_id, termin_id, tip_pregleda_id, obavljen, obrisan) VALUES (5, 3, 1, 1, 1, 1, 0, 0);
INSERT INTO pregled (lekar_id, sala_id, klinika_pregleda_id, pacijent_id, termin_id, tip_pregleda_id, obavljen, obrisan) VALUES (5, 2, 1, 1, 1, 1, 0, 0);
INSERT INTO pregled (lekar_id, sala_id, klinika_pregleda_id, pacijent_id, termin_id, tip_pregleda_id, obavljen, obrisan) VALUES (5, null, 1, 1, 2, 1, 0, 0);

INSERT INTO operacija (lekar_id, sala_operacije_id, klinika_operacije_id, pacijent_id, termin_id, obavljena, obrisana) VALUES (5, 3, 1, 1, 3, 0, 0);
INSERT INTO operacija (lekar_id, sala_operacije_id, klinika_operacije_id, pacijent_id, termin_id, obavljena, obrisana) VALUES (5, null, 1, 1, 1, 0, 0);

INSERT INTO zdravstveni_karton (broj, pacijent_id, visina, masa) VALUES ('112', 1, '198.2', '99.0');
INSERT INTO zdravstveni_karton (broj, pacijent_id, visina, masa) VALUES ('113', 8, '178.1', '59');

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
INSERT INTO korisnik_authority (user_id, authority_id) VALUES (7, 1);
INSERT INTO korisnik_authority (user_id, authority_id) VALUES (7, 4);
INSERT INTO korisnik_authority (user_id, authority_id) VALUES (8, 1);
INSERT INTO korisnik_authority (user_id, authority_id) VALUES (8, 2);

INSERT INTO admin_klinike_klinika (klinika_id, admin_id) VALUES (1, 4);

INSERT INTO dijagnoza(naziv, sifra) VALUES("Mono", 1);
INSERT INTO dijagnoza(naziv, sifra) VALUES("Hemoroidi", 2);
INSERT INTO dijagnoza(naziv, sifra) VALUES("Grip", 3);
INSERT INTO dijagnoza(naziv, sifra) VALUES("Rak", 4);
INSERT INTO zdravstveni_karton_dijagnoze(zdravstveni_karton_id, dijagnoze_id) VALUES (2, 1);
INSERT INTO zdravstveni_karton_dijagnoze(zdravstveni_karton_id, dijagnoze_id) VALUES (2, 2);
INSERT INTO zdravstveni_karton_dijagnoze(zdravstveni_karton_id, dijagnoze_id) VALUES (2, 3);
INSERT INTO zdravstveni_karton_dijagnoze(zdravstveni_karton_id, dijagnoze_id) VALUES (2, 4);

INSERT INTO pregled (lekar_id, sala_id, klinika_pregleda_id, pacijent_id, termin_id, tip_pregleda_id, obavljen) VALUES (5, 1, 1, 8, 1, 1, 1);
INSERT INTO pregled (lekar_id, sala_id, klinika_pregleda_id, pacijent_id, termin_id, tip_pregleda_id, obavljen) VALUES (6, 1, 1, 8, 1, 2, 1);

INSERT INTO zdravstveni_karton_pregledi(zdravstveni_karton_id, pregledi_id) VALUES (2, 3);
INSERT INTO zdravstveni_karton_pregledi(zdravstveni_karton_id, pregledi_id) VALUES (2, 4);