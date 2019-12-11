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

            for(let klinika of klinike)
            {
                var btn = document.createElement("BUTTON");
                btn.classList.add("btn-list", "btn--radius-2", "btn--light-blue");
                btn.innerHTML = klinika.naziv;
                btn.id = klinika.naziv;
                /*btn.onclick = function(){

                }*/
                /*btn.click(
                    function(){
                        infoKlinike(klinika.naziv);
                    });*/
                btn.onclick = infoKlinike(klinika.naziv);
                document.getElementById("content").appendChild(btn);
            }

            $("#content").fadeIn(500);
        }

    });

}

function infoKlinike(naziv)
{
    return function(){
        // Get the modal
        var modal = document.getElementById("myModal");
        var p = document.getElementById("nazivKlinike");
        p.innerHTML = "";
        p.append("Naziv klinike: " + naziv);
        // When the user clicks on the button, open the modal
        modal.style.display = "block";

        // Get the <span> element that closes the modal
        var span = document.getElementsByClassName("close")[0];

        // When the user clicks on <span> (x), close the modal
        span.onclick = function() {
            modal.style.display = "none";
        }

        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    }

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