INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja) VALUES ('Pacijent', 'user', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marko', 'Markovic', 'user@example.com', 1, '2017-10-01 21:58:58', '2000-01-01 18:57:58');
INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja) VALUES ('AdministratorKlinickogCentra', 'admin', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nikola', 'Nikolic','admin@example.com', 1, '2017-10-01 18:57:58', '1997-10-01 18:57:58');
INSERT INTO klinika (naziv, lokacija, br_lekara, br_sala, img_path) VALUES ('Klinika 1', 'Janka Veselinovica, 20', 10, 4, 'images/icons/favicon.ico');

INSERT INTO AUTHORITY (name) VALUES ('ROLE_USER');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_PACIJENT');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_DOKTOR');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_MEDICINSKA_SESTRA');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMIN_KLINIKE');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMIN');

INSERT INTO korisnik_AUTHORITY (user_id, authority_id) VALUES (1, 1);
INSERT INTO korisnik_AUTHORITY (user_id, authority_id) VALUES (1, 2);
INSERT INTO korisnik_AUTHORITY (user_id, authority_id) VALUES (2, 1);
INSERT INTO korisnik_AUTHORITY (user_id, authority_id) VALUES (2, 6);
