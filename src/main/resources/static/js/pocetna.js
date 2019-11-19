$(document).ready(function(){

        $.get({
            url: 'api/korisnici/'+"proba"+'/',
            contentType: 'application/json',
            success: function(korisnik) {
                console.log(korisnik.uloga)
                switch (korisnik.uloga) {
                    case "PACIJENT": pocetnaPacijent(korisnik)
                    case "ADMIN_KLINICKOG_CENTRA": pocetnaAdminKlinickogCentra(korisnik)
                }
            },
            error: function() {
                alert("Neuspešno.")
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
        generisiFormuZaNivuKliniku()
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

function pocetnaPacijent(korisnik) {

    var nazivi = ["Klinike", "Vasa istorija", "Zdravstveni karton", korisnik.korisnickoIme];

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
            case korisnik.korisnickoIme:
                btn.id = "profilBtn"
                break;

        }
    }
}

function pocetnaAdminKlinickogCentra() {
    var nazivi = ["Zahtevi za registraciju", "Registruj klinike", "Sifarnik", "Dodaj administratora", korisnik.korisnickoIme];

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
            case korisnik.korisnickoIme:
                btn.id = "profilBtn"
                break;
            case "Dodaj administratora":
                btn.id = "dodajAdministratoraBtn"
                break;
        }
    }
}

function generisiKlinike() {
    document.getElementById("content").innerHTML = "";
    var textnode = document.createTextNode("Jos uvijek nije registrovana nijedna klinika u sistemu.");
    document.getElementById("content").appendChild(textnode);
}

function generisiIstoriju() {
    document.getElementById("content").innerHTML = "";
    var textnode = document.createTextNode("Jos uvijek nije dosupna istorija.");
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
    
}

function generisiFormuZaNivuKliniku() {

}

function generisiFormuZaSifanik() {

}

function generisiFormuZaNovogAdmina() {

}