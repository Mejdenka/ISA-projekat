INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja, confirmed) VALUES ('Pacijent', 'user', '$2a$10$e.g6dKlg/9jbUZFp4IcdnOAFJZbydpsge2ODaRY4b3uvDN9eNsjZK', 'Marko', 'Markovic', 'user@example.com', 1, '2017-10-01 21:58:58', '2000-01-01 18:57:58', 1);
INSERT INTO korisnik (dtype, korisnicko_ime, lozinka, ime, prezime, email, enabled, last_password_reset_date, datum_rodjenja, confirmed) VALUES ('AdministratorKlinickogCentra', 'admin', '$2a$10$e.g6dKlg/9jbUZFp4IcdnOAFJZbydpsge2ODaRY4b3uvDN9eNsjZK', 'Nikola', 'Nikolic','admin@example.com', 1, '2017-10-01 18:57:58', '1997-10-01 18:57:58', 1);
INSERT INTO klinika (naziv) VALUES ('Klinika 1');
INSERT INTO klinika (naziv) VALUES ('Klinika 2');
INSERT INTO klinika (naziv) VALUES ('Klinika 3');
INSERT INTO klinika (naziv) VALUES ('Klinika 4');
INSERT INTO klinika (naziv) VALUES ('Klinika 5');

INSERT INTO authority (name) VALUES ('ROLE_USER');
INSERT INTO authority (name) VALUES ('ROLE_PACIJENT');
INSERT INTO authority (name) VALUES ('ROLE_DOKTOR');
INSERT INTO authority (name) VALUES ('ROLE_MEDICINSKA_SESTRA');
INSERT INTO authority (name) VALUES ('ROLE_ADMIN_KLINIKE');
INSERT INTO authority (name) VALUES ('ROLE_ADMIN');

INSERT INTO korisnik_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO korisnik_authority (user_id, authority_id) VALUES (1, 2);
INSERT INTO korisnik_authority (user_id, authority_id) VALUES (2, 1);
INSERT INTO korisnik_authority (user_id, authority_id) VALUES (2, 6);
