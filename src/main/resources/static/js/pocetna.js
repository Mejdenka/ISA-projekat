$(document).ready(function(){
    let token = JSON.parse(localStorage.getItem('jwt'))
    var ulogovan = null;
    $.ajax
    ({
        type: "GET",
        url: 'api/korisnici/whoami',
        contentType: 'application/json',
        headers: {
            'Authorization': 'Bearer ' + token
        },
        success: function (user){
            ulogovan = user;
            console.log(user.username)
        }
    });

    //kako preko securityja unauhorized pristup?
    //i refresh kesa pa ako nema ulogovanog baci zabranjenu stranicu
    //ovdje problem s null objektom rijesi tako sto ces  zabraniti back dugme!!!!!!!!!!!!!!!!!!!!!!!!
    //Ovaj poziv mozda i ne treba jer u ulogovanom imamo listu authorities i logika iz kontrolera se moze prenijeti ovdje

    $.ajax
    ({
        type: "GET",
        url: 'api/korisnici/getMyAuthority',
        contentType: 'application/json',
        headers: {
            'Authorization': 'Bearer ' + token
        },
        success: function (authority){
            switch (authority) {
                case "ROLE_PACIJENT":
                    pocetnaPacijent(ulogovan);
                    break;
                case "ROLE_ADMIN":
                    pocetnaAdminKlinickogCentra(ulogovan);
                    break;
                default:
                    console.log("Jos nismo napravili pocetne za ostale korisnike :)")
            }
        }
    });


    //************************************************************************************************************************
    //funkcije dugmadi za PACIJENTA
    $('body').on('click', '#klinikeBtn', function(e) {
        generisiKlinike();
    });
    $('body').on('click', '#istorijaBtn', function(e) {
        generisiIstoriju()
    });
    $('body').on('click', '#zdravstveniKartonBtn', function(e) {
        generisiZdravstveniKarton();
    });
    //************************************************************************************************************************
    //funkcije dugmadi za ADMINA KLINICKOG CENTRA
    $('body').on('click', '#zahtevZaRegistracijuBtn', function(e) {
        generisiZahteveZaRegistraciju();
    });
    $('body').on('click', '#registracijaKlinikaBtn', function(e) {
        generisiFormuZaNovuKliniku()
    });
    $('body').on('click', '#sifarnikBtn', function(e) {
        generisiFormuZaSifanik();
    });
    $('body').on('click', '#dodajAdministratoraBtn', function(e) {
        generisiFormuZaNovogAdmina();
    });
    //************************************************************************************************************************
    //funkcija profil-dugmeta
    $('body').on('click', '#profilBtn', function(e) {
        generisiProfil();
    });

});

function pocetnaPacijent(ulogovan) {
    korisnik = ulogovan;
    var imeKorisnika = korisnik.ime + " " + korisnik.prezime;
    var nazivi = ["Klinike", "Vasa istorija", "Zdravstveni karton", imeKorisnika];

    for(let naziv of nazivi) {
        //pravljenje dugmadi
        var btn = document.createElement("BUTTON");
        btn.classList.add("btn", "btn--radius-2", "btn--light-blue");
        btn.innerHTML = naziv;
        document.getElementById("navbar").appendChild(btn);
        //dodjela specificnih id-jeva dugmadima
        switch (naziv) {
            case "Klinike":
                btn.id = "klinikeBtn"
                break;
            case "Vasa istorija":
                btn.id = "istorijaBtn"
                break;
            case "Zdravstveni karton":
                btn.id = "zdravstveniKartonBtn"
                break;
            case imeKorisnika:
                btn.id = "profilBtn"
                break;

        }
    }
}

function pocetnaAdminKlinickogCentra(korisnik) {
    //korisnik = ulogovan;
    var imeKorisnika = korisnik.ime + " " + korisnik.prezime;
    var nazivi = ["Zahtevi za registraciju", "Registruj klinike", "Sifarnik", "Dodaj administratora", imeKorisnika];

    for(let naziv of nazivi) {
        //pravljenje dugmadi
        var btn = document.createElement("BUTTON");
        btn.classList.add("btn", "btn--radius-2", "btn--light-blue");
        btn.innerHTML = naziv;
        document.getElementById("navbar").appendChild(btn);
        //dodjela specificnih id-jeva dugmadima
        switch (naziv) {
            case "Zahtevi za registraciju":
                btn.id = "zahtevZaRegistracijuBtn"
                break;
            case "Registruj klinike":
                btn.id = "registracijaKlinikaBtn"
                break;
            case "Sifarnik":
                btn.id = "sifarnikBtn"
                break;
            case imeKorisnika:
                btn.id = "profilBtn"
                break;
            case "Dodaj administratora":
                btn.id = "dodajAdministratoraBtn"
                break;
        }
    }
}

function generisiKlinike() {
    var content = document.getElementById("content")
    content.innerHTML = "";

    $.get({

        url:'api/klinike/getAll',
        contentType: 'application/json',
        headers: {
            'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
        },
        success: function(klinike)
        {
            let naslov = $('<h3 id=naslov>Klinike:</h3>');
            let tabela = $('<table id="tabelaKlinika"> <thead> <tr> <th>Naziv klinike</th></tr></thead><tbody> </tbody></table>');
            $("#content").append(naslov);
            $("#content").append(tabela);

            for(let klinika of klinike)
            {
                dodajKlinike(klinika);
            }

            $("#content").fadeIn(500);
        }

    });

}

function dodajKlinike(klinika)
{
    let tr=$('<tr></tr>');

    let klinikaDugme = $('<td><button class = "button1" name="'+klinika.naziv+'" id="klinikaBtn">'+klinika.naziv+'</button></td>');
    /*klinikaDugme.click(
        function(){
            prikaziInfoKlinike(klinika);
        });*/

    tr.append(klinikaDugme);
    $('#tabelaKlinika tbody').append(tr);

    //$('#tabelaKlinika tbody').append(klinikaDugme);


}

function generisiIstoriju() {
    document.getElementById("content").innerHTML = "";
    var textnode = document.createTextNode("Jos uvijek nije dostupna istorija.");
    document.getElementById("content").appendChild(textnode);
}

function generisiZdravstveniKarton() {
    document.getElementById("content").innerHTML = "";
    var textnode = document.createTextNode("Jos uvijek nije dostupan zdravstveni karton.");
    document.getElementById("content").appendChild(textnode);
}

function generisiProfil() {
    document.getElementById("content").innerHTML = "";
    var textnode = document.createTextNode("Informacije: ");
    document.getElementById("content").appendChild(textnode);
}

function generisiZahteveZaRegistraciju() {
    document.getElementById("content").innerHTML = "";
    var textnode = document.createTextNode("Nemate zahteva za registraciju.");
    document.getElementById("content").appendChild(textnode);
}

function generisiFormuZaNovuKliniku() {
    document.getElementById("content").innerHTML = "";
    var textnode = document.createTextNode("Jos uvijek nije dostupna forma za novu kliniku.");
    document.getElementById("content").appendChild(textnode);
}

function generisiFormuZaSifanik() {
    document.getElementById("content").innerHTML = "";
    var textnode = document.createTextNode("Jos uvijek nije dostupna forma za sifarnik.");
    document.getElementById("content").appendChild(textnode);
}

function generisiFormuZaNovogAdmina() {
    document.getElementById("content").innerHTML = "";
    var textnode = document.createTextNode("Jos uvijek nije dostupna forma za novog admina.");
    document.getElementById("content").appendChild(textnode);
}