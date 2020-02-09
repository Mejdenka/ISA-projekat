# Naša mala klinika

## Uvod
Naša mala klinika je informacioni sistem kliničkog centra preko kojeg pacijenti mogu da zakazuju preglede. Pristup sistemu
imaju i medicinske sestre i ljekari koji mogu da unose izvještaje o izvršenim pregledima i operacijama, kao i da zakazuju
iste za pacijente. Klinički centar se sastoji iz više usko specijalizovanih klinika koje su registrovane u okviru informacionog
sistema. Osnovna namjena aplikacije je vođenje evidencije o zaposlenima, registrovanim klinikama, salama za preglede i operacije,         
pacijentima i njihovim zdravstvenim kartonima, kao i zakazivanje pregleda.

Naša mala klinika napravljena je u okviru predmetnog zadatka iz predmeta Internet softverske arhitekture i Projektovanje softvera 
na studijskom programu Računarstvo i automatika Fakulteta tehničkih nauka Univerziteta u Novom Sadu. 

## Autori
- [Jelena Garić](https://github.com/Mejdenka)
- [Marina Simić](https://github.com/marinasimic)
- [Stefan Šumar](https://github.com/stefansumar)

## Podešavanje okruženja
### Potreban softver za pokretanje projekta
* Java 8
* SpringBoot
* Maven 3.6.1
* MySql 8.0

#### Opciono
* IntelliJ IDEA (u tutorijalu je korišćena verzija 2019.3.1)
* MySQL Workbench (u tutorijalu je korišćena verzija 8.0)

### Potreban softver za pokretanje testova
- JUnit
- Google Chrome 80

### Pokretanje
 1. Pokrenite MySQL Workbench i napravite bazu podataka kojoj ćete dati naziv i kredencijale navedene [ovde](https://github.com/TimJV20/ISA-projekat/blob/master/src/main/resources/application.properties).
    `spring.datasource.url = jdbc:mysql://localhost:3306/klinickicentar?useSSL=false&createDatabaseIfNotExist=true&serverTimezone=UTC`
    
    `spring.datasource.username=root`
    
    `spring.datasource.password=timJV20`
    
    U primjeru iznad naziv baze je klinickicentar, korisničko ime je root, a lozinka timJV20.
    
 2. Pokrenite IntelliJ i izaberite opciju **Get from Version Control**. U polju **URL** unesite `https://github.com/TimJV20/ISA-projekat`,
    a u polju **Directory** izaberite lokaciju na vašem računaru gdje želite da izvorni kod bude sačuvan. Nakon toga kliknite na Clone.
    ![Izgled prozora](https://i.imgur.com/XnCMizn.png)
 
 3. U gornjem desnom uglu prozora kliknite na **Add Configuration**, zatim u novootvorenom prozoru kliknite na + (slika ispod)
    i iz padajućeg menija izaberite **Application**. U polju **Main Class** izaberite *IsapswApplication*, а zatim kliknite na **OK**.
    ![Korak 1.](https://i.imgur.com/b8BFAUt.png)
    
    ![Korak 2.](https://i.imgur.com/QV4pCbi.png)
    
    ![Korak 3.](https://i.imgur.com/eO5tCLc.png)
 
 4. Sada je samo potrebno da kliknete na **Run** i aplikacija će biti dostupna na *[localhost:1111](https://localhost:1111/)*.
 
 
 
