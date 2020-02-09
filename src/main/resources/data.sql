INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja, confirmed, klinika_pacijenta_id, jbo, karton_id, obrisan, promijenjena_lozinka) VALUES ('Pacijent', 'user', '$2a$10$e.g6dKlg/9jbUZFp4IcdnOAFJZbydpsge2ODaRY4b3uvDN9eNsjZK', 'Marko', 'Markovic', 'user@example.com', 1, '2017-10-01 21:58:58', '2000-01-01 18:57:58', 1, 1, '1512997186555', 1, 0, 1);
INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja, confirmed, obrisan, promijenjena_lozinka) VALUES ('AdministratorKlinickogCentra', 'admin', '$2a$10$e.g6dKlg/9jbUZFp4IcdnOAFJZbydpsge2ODaRY4b3uvDN9eNsjZK', 'Nikola', 'Nikolic','admin@example.com', 1, '2017-10-01 18:57:58', '1997-10-01 18:57:58', 1, 0, 1);
INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja, confirmed, obrisan, promijenjena_lozinka) VALUES ('AdministratorKlinickogCentra', 'adminPero', '$2a$10$e.g6dKlg/9jbUZFp4IcdnOAFJZbydpsge2ODaRY4b3uvDN9eNsjZK', 'Pero', 'Peric','pero.peric@example.com', 1, '2017-10-01 18:57:58', '1997-10-01 18:57:58', 1, 0, 1);
INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja, confirmed, obrisan, klinika_admina_id, promijenjena_lozinka) VALUES ('AdministratorKlinike', 'adminK', '$2a$10$e.g6dKlg/9jbUZFp4IcdnOAFJZbydpsge2ODaRY4b3uvDN9eNsjZK', 'Jovan', 'Jovanovic','adminKlinike@example.com', 1, '2017-10-01 18:57:58', '1997-10-01 18:57:58', 1, 0, 1, 1);
INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja, confirmed, klinika_lekara_id, slobodan, na_godisnjem, zbir_ocena, broj_ocena, obrisan, radno_vreme, traje_pregled, promijenjena_lozinka) VALUES ('Lekar', 'lekar','$2a$10$e.g6dKlg/9jbUZFp4IcdnOAFJZbydpsge2ODaRY4b3uvDN9eNsjZK', 'Jovana', 'Jovanovic','lekar@example.com', 1,'2017-10-01 18:57:58', '1997-10-01 18:57:58', 1, 1, 1, 0, 5, 1, 0, '08:00-16:00', 0, 1);
INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja, confirmed, klinika_lekara_id, slobodan, na_godisnjem, zbir_ocena, broj_ocena, obrisan, radno_vreme, traje_pregled, promijenjena_lozinka) VALUES ('Lekar', 'probaLekar','$2a$10$e.g6dKlg/9jbUZFp4IcdnOAFJZbydpsge2ODaRY4b3uvDN9eNsjZK', 'Amelie', 'Lens','amelie@example.com', 1, '2017-10-01 18:57:58','1997-10-01 18:57:58', 1, 1, 0, 0, 4, 1, 0, '08:30-16:30', 0, 1);
INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja, confirmed, klinika_med_sestre_id, obrisan, promijenjena_lozinka) VALUES('MedicinskaSestra', 'sestra', '$2a$10$e.g6dKlg/9jbUZFp4IcdnOAFJZbydpsge2ODaRY4b3uvDN9eNsjZK', 'Svetlana', 'Micic','sestra@example.com', 1,'2017-10-01 18:57:58', '1997-10-01 18:57:58', 1, 1, 0, 1);
INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja, confirmed, klinika_pacijenta_id, jbo, karton_id, obrisan, promijenjena_lozinka) VALUES ('Pacijent', 'pacijent', '$2a$10$e.g6dKlg/9jbUZFp4IcdnOAFJZbydpsge2ODaRY4b3uvDN9eNsjZK', 'Marina', 'Simic', 'userka@example.com', 1, '2017-10-01 21:58:58', '2000-01-01 18:57:58', 1, 1, '1512997186666', 2, 0, 1);
INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja, confirmed, klinika_lekara_id, slobodan, na_godisnjem, zbir_ocena, broj_ocena, obrisan, radno_vreme, traje_pregled, promijenjena_lozinka) VALUES ('Lekar', 'ljekar','$2a$10$e.g6dKlg/9jbUZFp4IcdnOAFJZbydpsge2ODaRY4b3uvDN9eNsjZK', 'Pero', 'Peric','pero@example.com', 1, '2017-10-01 18:57:58','1997-10-01 18:57:58', 1, 2, 0, 0, 4, 1, 0, '08:30-16:30', 0, 1);
INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja, confirmed, klinika_lekara_id, slobodan, na_godisnjem, zbir_ocena, broj_ocena, obrisan, radno_vreme, traje_pregled, promijenjena_lozinka) VALUES ('Lekar', 'karlje','$2a$10$e.g6dKlg/9jbUZFp4IcdnOAFJZbydpsge2ODaRY4b3uvDN9eNsjZK', 'Simo', 'Simic','simo@example.com', 1, '2017-10-01 18:57:58','1997-10-01 18:57:58', 1, 3, 0, 0, 4, 1, 0, '08:30-16:30', 0, 1);
INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja, confirmed, klinika_lekara_id, slobodan, na_godisnjem, zbir_ocena, broj_ocena, obrisan, radno_vreme, traje_pregled, promijenjena_lozinka) VALUES ('Lekar', 'doca','$2a$10$e.g6dKlg/9jbUZFp4IcdnOAFJZbydpsge2ODaRY4b3uvDN9eNsjZK', 'Doca', 'Docic','doco@example.com', 1, '2017-10-01 18:57:58','1997-10-01 18:57:58', 1, 4, 0, 0, 4, 1, 0, '08:30-16:30', 0, 1);


INSERT INTO klinika (naziv, lokacija, opis, prosecna_ocena, zbir_ocena, broj_ocena, lokacija_na_mapi_id) VALUES ('Klinika 1', 'Janka Veselinovica, 20',
'Prva klinika u centru.', 4.5, 9, 2, 1);

INSERT INTO klinika (naziv, lokacija, opis, zbir_ocena, broj_ocena, lokacija_na_mapi_id) VALUES ('Klinika 1', 'Janka Veselinovica, 20', 'Prva klinika u centru.', 4, 1, 1);
INSERT INTO klinika (naziv, lokacija, opis, zbir_ocena, broj_ocena, lokacija_na_mapi_id) VALUES ('Klinika 2', 'Gogoljeva, 30', 'Druga klinika u centru.', 3, 1, 1);
INSERT INTO klinika (naziv, lokacija, opis, zbir_ocena, broj_ocena, lokacija_na_mapi_id) VALUES ('Klinika 3', 'Alekse Santica, 22', 'Treca klinika u centru.', 3, 1, 1);
INSERT INTO klinika (naziv, lokacija, opis, zbir_ocena, broj_ocena, lokacija_na_mapi_id) VALUES ('Klinika 4', 'Bulevar Cara Lazara, 30', 'Cetvrta klinika u centru.', 7, 2, 1);


INSERT INTO lokacija (broj_ulice, lat, lon) VALUES ('Janka Veselinovica, 20', 45.261608, 19.815776);

INSERT INTO sala (naziv, broj, slobodna, rezervisana, obrisana, klinika_sale_id) VALUES ('Sala 1', 1, 1, 1, 0, 1);
INSERT INTO sala (naziv,  broj, slobodna, rezervisana, obrisana, klinika_sale_id) VALUES ('Sala 2', 2, 0, 0, 0, 1);
INSERT INTO sala (naziv,  broj, slobodna, rezervisana, obrisana, klinika_sale_id) VALUES ('Sala 3', 3, 1, 0, 0, 1);

INSERT INTO tip_pregleda (naziv, cena, klinika_id, obrisan) VALUES ('Pregled glave', 100, 1, 0);
INSERT INTO tip_pregleda (naziv, cena, klinika_id, obrisan) VALUES ('Pregled srca', 200, 1, 0);
INSERT INTO tip_pregleda (naziv, cena, klinika_id, obrisan) VALUES ('Pregled glave', 100, 2, 0);
INSERT INTO tip_pregleda (naziv, cena, klinika_id, obrisan) VALUES ('Pregled srca', 100, 2, 0);
INSERT INTO tip_pregleda (naziv, cena, klinika_id, obrisan) VALUES ('Pregled glave', 200, 3, 0);
INSERT INTO tip_pregleda (naziv, cena, klinika_id, obrisan) VALUES ('Pregled srca', 300, 3, 0);
INSERT INTO tip_pregleda (naziv, cena, klinika_id, obrisan) VALUES ('Pregled glave', 200, 4, 0);
INSERT INTO tip_pregleda (naziv, cena, klinika_id, obrisan) VALUES ('Pregled srca', 400, 4, 0);

INSERT INTO termin (pocetak, kraj, rezervisan, klinika_termina_id, obrisan, pacijent_id) VALUES ('2020-02-07 19:20:00', '2020-02-07 19:50:00', 1, 1, 0, 1);
INSERT INTO termin (pocetak, kraj, rezervisan, klinika_termina_id, obrisan, pacijent_id) VALUES ('2020-02-13 12:50:00', '2020-02-13 13:20:00', 1, 1, 0, 8);
INSERT INTO termin (pocetak, kraj, rezervisan, klinika_termina_id, obrisan, pacijent_id) VALUES ('2020-02-14 10:30:00', '2020-02-14 16:00:00', 1, 1, 0, 8);
INSERT INTO termin (pocetak, kraj, rezervisan, klinika_termina_id, obrisan, pacijent_id) VALUES ('2020-02-10 10:30:00', '2020-02-10 16:00:00', 1, 1, 0, 1);
INSERT INTO termin (pocetak, kraj, rezervisan, klinika_termina_id, obrisan, pacijent_id) VALUES ('2020-02-11 12:00:00', '2020-02-11 12:00:00', 0, 1, 0, null);

INSERT INTO godisnji_odsustvo_termin (pocetak, kraj, godisnji, odsustvo, obrisan, odobren, lekargo_id, lekar_ods_id, med_sestra_go_id, med_sestra_ods_id)
VALUES ('2020-02-14 10:30:00', '2020-02-14 16:00:00', 0, 1, 0, 0, null, null, null, 7);
INSERT INTO godisnji_odsustvo_termin (godisnji, odsustvo, pocetak, kraj, obrisan, lekargo_id, lekar_ods_id, odobren, med_sestra_go_id, med_sestra_ods_id) VALUES
 (1, 0, '2020-02-14 01:01:00', '2020-02-22 11:11:00', 0, 5, null, 0, null, null);


INSERT INTO pregled (lekar_id, sala_id, klinika_pregleda_id, pacijent_id, termin_id, tip_pregleda_id, obavljen, obrisan, lekar_pregleda_id) VALUES
(5, 3, 1, 1, 1, 1, 0, 0, 5);

INSERT INTO pregled (lekar_id, sala_id, klinika_pregleda_id, pacijent_id, termin_id, tip_pregleda_id, obavljen, obrisan, lekar_pregleda_id) VALUES (5, 3, 1, 1, 2, 2, 0, 0, 5);

INSERT INTO pregled (lekar_id, sala_id, klinika_pregleda_id, pacijent_id, termin_id, tip_pregleda_id, obavljen, obrisan, lekar_pregleda_id) VALUES (5, 2, 1, 8, 1, 1, 1, 0, 5);
INSERT INTO pregled (lekar_id, sala_id, klinika_pregleda_id, pacijent_id, termin_id, tip_pregleda_id, obavljen, obrisan, lekar_pregleda_id) VALUES (5, 1, 1, 8, 4, 1, 0, 0, 5);
INSERT INTO pregled (lekar_id, sala_id, klinika_pregleda_id, pacijent_id, termin_id, tip_pregleda_id, obavljen, obrisan, lekar_pregleda_id) VALUES (5, 3, 1, 1, 3, 1, 0, 0, 5);
INSERT INTO pregled (lekar_id, sala_id, klinika_pregleda_id, pacijent_id, termin_id, tip_pregleda_id, obavljen, obrisan, lekar_pregleda_id) VALUES (5, 2, 1, 1, 2, 1, 0, 0, 5);
INSERT INTO pregled (lekar_id, sala_id, klinika_pregleda_id, pacijent_id, termin_id, tip_pregleda_id, obavljen, obrisan, lekar_pregleda_id) VALUES (5, null, 1, null, 5, 1, 0, 0, 5);
INSERT INTO pregled (lekar_id, sala_id, klinika_pregleda_id, pacijent_id, termin_id, tip_pregleda_id, obavljen, obrisan, lekar_pregleda_id) VALUES (9, null, 2, null, 5, 1, 0, 0, 9);
INSERT INTO pregled (lekar_id, sala_id, klinika_pregleda_id, pacijent_id, termin_id, tip_pregleda_id, obavljen, obrisan, lekar_pregleda_id) VALUES (10, null, 3, null, 5, 1, 0, 0, 10);
INSERT INTO pregled (lekar_id, sala_id, klinika_pregleda_id, pacijent_id, termin_id, tip_pregleda_id, obavljen, obrisan, lekar_pregleda_id) VALUES (11, null, 4, null, 5, 1, 0, 0, 11);

INSERT INTO operacija (lekar_id, sala_operacije_id, klinika_operacije_id, pacijent_id, termin_id, obavljena, obrisana) VALUES (5, 3, 1, 8, 3, 0, 0);
INSERT INTO operacija (lekar_id, sala_operacije_id, klinika_operacije_id, pacijent_id, termin_id, obavljena, obrisana) VALUES (5, null, 1, 8, 1, 0, 0);

INSERT INTO zdravstveni_karton (broj, pacijent_id, visina, masa, dioptrija, krvna_grupa, alergije) VALUES ('112', 1, '198.2', '99.0', 'A+', 'nema', 'Polen, gluten');
INSERT INTO zdravstveni_karton (broj, pacijent_id, visina, masa, dioptrija, krvna_grupa, alergije) VALUES ('113', 8, '178.1', '59', '0+', '+1 -1', 'Jagode');

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

INSERT INTO lek(id, naziv_leka, sifra_leka, obrisan) VALUES (1, 'Brufen 500mg', 'Brf5', 0);
INSERT INTO lek(id, naziv_leka, sifra_leka, obrisan) VALUES (2, 'Brufen 600mg', 'Brf6', 0);
INSERT INTO lek(id, naziv_leka, sifra_leka, obrisan) VALUES (3, 'Cefaleksin', 'Cef1', 0);

INSERT INTO dijagnoza(id, naziv_dijagnoze, sifra_dijagnoze) VALUES (1, 'Mononukleoza', 'Mn1t');
INSERT INTO dijagnoza(id, naziv_dijagnoze, sifra_dijagnoze) VALUES (2, 'Kandida', 'Sml1');
INSERT INTO dijagnoza(id, naziv_dijagnoze, sifra_dijagnoze) VALUES (3, 'Hemoroidi', 'Hrm1');
INSERT INTO dijagnoza(id, naziv_dijagnoze, sifra_dijagnoze) VALUES (4, 'Trihineloza', 'Th1');

INSERT INTO zdravstveni_karton_dijagnoze(zdravstveni_karton_id, dijagnoze_id) values(2, 1);
INSERT INTO zdravstveni_karton_dijagnoze(zdravstveni_karton_id, dijagnoze_id) values(2, 2);

INSERT INTO zdravstveni_karton_pregledi(zdravstveni_karton_id, pregledi_id) values(2, 2);
INSERT INTO zdravstveni_karton_pregledi(zdravstveni_karton_id, pregledi_id) values(2, 3);