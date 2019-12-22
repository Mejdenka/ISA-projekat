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
                        case "ROLE_ADMIN_KLINIKE":
                            console.log(JSON.stringify(ulogovan))
                            pocetnaAdminKlinike(ulogovan);
                            break;
                        default:
                            console.log("Jos nismo napravili pocetne za ostale korisnike :)")
                    }
                    var div = document.createElement("div");
                    div.classList.add("dropdown");

                    var btn = document.createElement("BUTTON");
                    btn.classList.add("dropbtn", "btn--radius-2", "btn--light-blue");
                    btn.innerHTML = "&#9660;"

                    var div1 = document.createElement("div");
                    div1.classList.add("dropdown-content");

                    var a = document.createElement('a');
                    var linkText = document.createTextNode("Odjava");
                    a.appendChild(linkText);
                    a.id = "logOutBtn"

                    div1.appendChild(a);
                    div.appendChild(btn);
                    div.appendChild(div1);
                    document.getElementById("navbar").appendChild(div);
                    var dobrodoslica = document.createTextNode("Dobro došli " + ulogovan.ime + "!");
                    var span = document.createElement('span');
                    span.style.fontSize = "20px";
                    span.appendChild(dobrodoslica);
                    document.getElementById("content").appendChild(span);
                }
            });

         }//,
        // error: function(){
        //     window.location = "prijava.html";
        // }
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
    //funkcije dugmadi za ADMINA KLINIKE
    /*$('body').on('click', '#klinikaAdminaBtn', function(e) {
        generisiKlinikuAdmina();
    });*/
    //************************************************************************************************************************
    //funkcija profil-dugmeta
    $('body').on('click', '#profilBtn', function(e) {
        generisiProfil(ulogovan);
    });
    //funkcija za logOut
    $('body').on('click', '#logOutBtn', function(e) {
        logOut();
    });

});

function pocetnaAdminKlinike(ulogovan) {
    korisnik = ulogovan;
    var imeKorisnika = korisnik.ime + " " + korisnik.prezime;
    var nazivi = ["Vasa klinika", imeKorisnika];

    for(let naziv of nazivi) {
        //pravljenje dugmadi
        var btn = document.createElement("BUTTON");
        btn.classList.add("btn", "btn--radius-2", "btn--light-blue");
        btn.innerHTML = naziv;
        document.getElementById("navbar").appendChild(btn);
        //dodjela specificnih id-jeva dugmadima
        switch (naziv) {
            case "Vasa klinika":
                btn.id = "klinikaAdminaBtn"
                btn.onclick = generisiKlinikuAdmina(ulogovan.klinika);
                break;
            case imeKorisnika:
                btn.id = "profilBtn"
                break;

        }
    }

}

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

function generisiKlinikuAdmina(klinika) {
    return function(){
        $("#content").fadeOut(100, function(){
            document.getElementById("content").innerHTML = "";
            var textnode = document.createTextNode("Klinika: " + klinika.naziv);
            document.getElementById("content").appendChild(textnode);
        });
        $("#content").fadeIn(500);
    }
}

function generisiKlinike() {

    $("#content").fadeOut(100, function(){
        document.getElementById("content").innerHTML = "";
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
                    btn.onclick = infoKlinike(klinika);
                    document.getElementById("content").appendChild(btn);
                }


            }

        });
    });

    $("#content").fadeIn(500);
}

function infoKlinike(klinika)
{
    return function(){
        // Get the modal
        var modal = document.getElementById("klinikaModal");
        var tableRef = document.getElementById('infoKlinike').getElementsByTagName('tbody')[0];
        tableRef.innerHTML="";
        var podaciKlinike   = tableRef.insertRow();

        var nazivKlinike  = podaciKlinike.insertCell(0);
        var nazivText  = document.createTextNode(klinika.naziv);
        nazivKlinike.appendChild(nazivText);

        var lokacijaKlinike  = podaciKlinike.insertCell(1);
        var lokacijaText  = document.createTextNode(klinika.lokacija);
        lokacijaKlinike.appendChild(lokacijaText);

        var brLekara  = podaciKlinike.insertCell(2);
        var brLekaraText  = document.createTextNode(klinika.brLekara);
        brLekara.appendChild(brLekaraText);

        var brSala  = podaciKlinike.insertCell(3);
        var brSalaText  = document.createTextNode(klinika.brSala);
        brSala.appendChild(brSalaText);

            // When the user clicks on the button, open the modal
        //modal.style.display = "block";
        $("#klinikaModal").fadeIn(500);

        // Get the <span> element that closes the modal
        var span = document.getElementsByClassName("close")[0];

        // When the user clicks on <span> (x), close the modal
        span.onclick = function() {
            modal.style.display = "none";
        }

        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function(event) {
            if (event.target == modal) {
                //modal.style.display = "none";
                $("#klinikaModal").fadeOut(100);
            }
        }
    }

}

function generisiIstoriju() {
    $("#content").fadeOut(100, function(){
        document.getElementById("content").innerHTML = "";
        var textnode = document.createTextNode("Jos uvijek nije dostupna istorija.");
        document.getElementById("content").appendChild(textnode);
    });
    $("#content").fadeIn(500);
}

function generisiZdravstveniKarton() {
    $("#content").fadeOut(100, function(){
        document.getElementById("content").innerHTML = "";
        var textnode = document.createTextNode("Jos uvijek nije dostupan zdravstveni karton.");
        document.getElementById("content").appendChild(textnode);
    });
    $("#content").fadeIn(500);
}

function generisiProfil(korisnik) {
    $("#content").fadeOut(100, function(){
        var content = document.getElementById("content");
        content.innerHTML = "";

        var prviRed = document.createElement("var");
        prviRed.classList.add("row", "wrapper--w680");
        var varIme = document.createElement("var");
        varIme.classList.add("col-2", "input-group");
        var ime = document.createTextNode("Ime");
        varIme.appendChild(ime);
        varIme.appendChild(document.createElement("br"));
        var txtIme = document.createElement('input');
        txtIme.type = 'text';
        txtIme.id = "ime";
        txtIme.classList.add("input--style-4");
        txtIme.style.height = "40px";
        txtIme.style.width = "250px";
        txtIme.value = korisnik.ime;
        txtIme.disabled = "true";
        varIme.appendChild(txtIme);
        prviRed.appendChild(varIme);
        var varPrezime = document.createElement("var");
        varPrezime.classList.add("col-2", "input-group");
        var prezime = document.createTextNode("Prezime");
        varPrezime.appendChild(prezime);
        varPrezime.appendChild(document.createElement("br"));
        var txtPrezime = document.createElement('input');
        txtPrezime.type = 'text';
        txtPrezime.id = "prezime";
        txtPrezime.classList.add("input--style-4");
        txtPrezime.style.height = "40px"
        txtPrezime.style.width = "250px"
        txtPrezime.value = korisnik.prezime;
        txtPrezime.disabled = "true";
        content.appendChild(txtPrezime);
        varPrezime.appendChild(txtPrezime);
        prviRed.appendChild(varPrezime);
        content.appendChild(prviRed);

        var drugiRed = document.createElement("var");
        drugiRed.classList.add("row", "wrapper--w680");
        var varUsername = document.createElement("var");
        varUsername.classList.add("col-2", "input-group");
        var username = document.createTextNode("Korisničko ime");
        varUsername.appendChild(username);
        varUsername.appendChild(document.createElement("br"));
        var txtUsername = document.createElement('input');
        txtUsername.type = 'text';
        txtUsername.id = "username";
        txtUsername.classList.add("input--style-4");
        txtUsername.style.height = "40px"
        txtUsername.style.width = "250px"
        txtUsername.value = korisnik.username;
        txtUsername.disabled = "true";
        varUsername.appendChild(txtUsername);
        drugiRed.appendChild(varUsername);
        var varEmail = document.createElement("var");
        varEmail.classList.add("col-2", "input-group");
        var email = document.createTextNode("E-mail");
        varEmail.appendChild(email);
        varEmail.appendChild(document.createElement("br"));
        var txtEmail = document.createElement('input');
        txtEmail.type = 'email';
        txtEmail.id = "email";
        txtEmail.classList.add("input--style-4");
        txtEmail.style.height = "40px"
        txtEmail.style.width = "250px"
        txtEmail.value = korisnik.email;
        txtEmail.disabled = "true";
        varEmail.appendChild(txtEmail);
        drugiRed.appendChild(varEmail);
        content.appendChild(drugiRed);

        var varDatumRodjenja = document.createElement("var");
        varDatumRodjenja.classList.add("col-2", "input-group");
        var datumRodjenja = document.createTextNode("Datum rođenja");
        varDatumRodjenja.appendChild(datumRodjenja);
        varDatumRodjenja.appendChild(document.createElement("br"));
        var txtDatumRodjenja = document.createElement('input');
        txtDatumRodjenja.type = 'text';
        txtDatumRodjenja.id = "datumRodjenja";
        txtDatumRodjenja.classList.add("input--style-4");
        txtDatumRodjenja.style.height = "40px"
        txtDatumRodjenja.style.width = "250px"
        txtDatumRodjenja.value = korisnik.datumRodjenja.substr(0, 10);
        txtDatumRodjenja.disabled = "true";
        varDatumRodjenja.appendChild(txtDatumRodjenja);
        content.appendChild(varDatumRodjenja);

        var btnChangePass = document.createElement("BUTTON");
        btnChangePass.classList.add("btn2", "btn--light-blue");
        btnChangePass.innerHTML = "Promijeni lozinku";
        btnChangePass.onclick = function(){
            var modal = document.getElementById("requestModal");
            var p = document.getElementById("requestUsername");
            p.innerHTML = "";
            var staraLozinka = document.createTextNode("Stara lozinka    ");
            p.appendChild(staraLozinka);
            var txtStaraLozinka = document.createElement('input');
            txtStaraLozinka.type = 'password';
            txtStaraLozinka.id = "staraLozinka";
            txtStaraLozinka.classList.add("input--style-4");
            txtStaraLozinka.style.height = "40px";
            txtStaraLozinka.style.width = "250px";
            p.appendChild(txtStaraLozinka);
            p.appendChild(document.createElement("br"));
            p.appendChild(document.createElement("br"));
            var novaLozinka = document.createTextNode("Nova lozinka    ");
            p.appendChild(novaLozinka);
            var txtNovaLozinka = document.createElement('input');
            txtNovaLozinka.type = 'password';
            txtNovaLozinka.id = "novaLozinka";
            txtNovaLozinka.classList.add("input--style-4");
            txtNovaLozinka.style.height = "40px";
            txtNovaLozinka.style.width = "250px";
            p.appendChild(txtNovaLozinka);
            p.appendChild(document.createElement("br"));
            p.appendChild(document.createElement("br"));
            var novaLozinka2 = document.createTextNode("Ponovite lozinku    ");
            p.appendChild(novaLozinka2);
            var txtNovaLozinka2 = document.createElement('input');
            txtNovaLozinka2.type = 'password';
            txtNovaLozinka2.id = "novaLozinka2";
            txtNovaLozinka2.classList.add("input--style-4");
            txtNovaLozinka2.style.height = "40px"
            txtNovaLozinka2.style.width = "250px"
            p.appendChild(txtNovaLozinka2);
            p.appendChild(document.createElement("br"));
            p.appendChild(document.createElement("br"));

            var btnPromijeni = document.createElement("BUTTON");
            btnPromijeni.classList.add("btn2", "btn--green");
            btnPromijeni.innerHTML = "Promijeni";
            btnPromijeni.onclick = function(){
                if ( txtStaraLozinka.value === "" || txtNovaLozinka.value === "" || txtNovaLozinka2.value == ""){
                    alert("Nijedno polje ne smije ostati prazno!");
                    return;
                }

                if ( txtNovaLozinka.value.length < 5){
                    alert("Nova lozinka mora imati preko 5 karaktera!");
                    return;
                }

                if ( txtNovaLozinka.value != txtNovaLozinka2.value ){
                    alert("Lozinke se ne poklapaju!");
                    return;
                }

                if ( txtStaraLozinka.value == txtNovaLozinka.value ){
                    alert("Stara i nova lozinka se poklapaju!");
                    return;
                }

                var oldPassword = txtStaraLozinka.value;
                var newPassword = txtNovaLozinka.value;

                $.post({
                url: 'api/korisnici/changePass',
                headers: {
                    'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
                },
                data: JSON.stringify({oldPassword, newPassword}),
                contentType: 'application/json',
            success: function() {
                alert("Lozinka uspješno promijenjena.")
            }
        });
                modal.style.display = "none";
            }
            p.append(btnPromijeni);

            var btnOdustani = document.createElement("BUTTON");
            btnOdustani.classList.add("btn2", "btn--red");
            btnOdustani.innerHTML = "Odustani";
            btnOdustani.onclick = function(){
                modal.style.display = "none";
            }
            p.append(btnOdustani);

            modal.style.display = "block";

            // Get the <span> element that closes the modal
            var span = document.getElementsByClassName("close")[1];

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
        content.appendChild(document.createElement("br"));
        content.appendChild(document.createElement("br"));
        content.appendChild(btnChangePass);
    });
    $("#content").fadeIn(500);
}

function generisiZahteveZaRegistraciju() {
    document.getElementById("content").innerHTML = "";

    $.get({

        url:'api/pacijenti/getRequests',
        contentType: 'application/json',
        headers: {
            'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
        },
        success: function(korisnici)
        {
            if( korisnici.length == 0)
            {
                document.getElementById("content").innerHTML = "";
                var textnode = document.createTextNode("Trenutno nema aktivnih zahtjeva za registraciju!");
                document.getElementById("content").appendChild(textnode);
            }
            else
            {
                for(let korisnik of korisnici)
                {
                    var btn = document.createElement("BUTTON");
                    btn.classList.add("btn-list", "btn--radius-2", "btn--light-blue");
                    btn.innerHTML = korisnik.ime + " " + korisnik.prezime;
                    btn.id = korisnik.username;
                    btn.onclick = infoKorisnik(korisnik);
                    document.getElementById("content").appendChild(btn);
                }
            }
        }

    });
}

function infoKorisnik(korisnik)
{
    return function(){
        // Get the modal
        var modal = document.getElementById("requestModal");
        var p = document.getElementById("requestUsername");
        p.innerHTML = "";
        p.append("Ime: " + korisnik.ime);
        p.append(document.createElement("br"));
        p.append("Prezime: " + korisnik.prezime);
        p.append(document.createElement("br"));
        p.append("Korisničko ime: " + korisnik.username);
        p.append(document.createElement("br"));
        p.append("E-mail: " + korisnik.email);
        p.append(document.createElement("br"));
        p.append("Razlog: ");
        p.append(document.createElement("br"));
        var textbox = document.createElement('input');
        textbox.type = 'text';
        textbox.id = "rejectionReason";
        textbox.style.border="1px solid black";
        textbox.style.height = "40px"
        p.append(textbox);
        p.append(document.createElement("br"));
        p.append(document.createElement("br"));
        var btnPrihvati = document.createElement("BUTTON");
        btnPrihvati.classList.add("btn2", "btn--green");
        btnPrihvati.innerHTML = "Prihvati";
        btnPrihvati.onclick = function(){
            $.post({
                url: 'api/pacijenti/confirmRequest',
                data: korisnik.username,
                contentType: 'application/json',
                headers: {
                    'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
                },
                success: function() {
                    alert('Zahtjev za registraciju prihvacen!');
                    generisiZahteveZaRegistraciju();
                },
                error: function() {
                    alert("Prihvatanje zahtjeva za registraciju nije uspjelo!")
                }
            });
            modal.style.display = "none";
        }
        p.append(btnPrihvati);

        var btnOdbij = document.createElement("BUTTON");
        btnOdbij.classList.add("btn2", "btn--red");
        btnOdbij.innerHTML = "Odbij";
        btnOdbij.onclick = function(){
            if (document.getElementById("rejectionReason").value == "" ){
                alert("Morate unijeti razlog odbijanja");
                return;
            }
            $.post({
                url: 'api/pacijenti/rejectRequest',
                data: korisnik.username,
                contentType: 'application/json',
                headers: {
                    'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
                },
                success: function() {
                    alert('Zahtjev za registraciju odbijen!');
                    generisiZahteveZaRegistraciju();
                },
                error: function() {
                    alert("Odbijanje zahtjeva za registraciju nije uspjelo!")
                }
            });
            modal.style.display = "none";
        }
        p.append(btnOdbij);
        // When the user clicks on the button, open the modal
        modal.style.display = "block";

        // Get the <span> element that closes the modal
        var span = document.getElementsByClassName("close")[1];

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

function generisiFormuZaNovuKliniku() {
    $("#content").fadeOut(100, function(){
        document.getElementById("content").innerHTML = "";
        var textnode = document.createTextNode("Jos uvijek nije dostupna forma za novu kliniku.");
        document.getElementById("content").appendChild(textnode);
    });
    $("#content").fadeIn(500);
}

function generisiFormuZaSifanik() {

    $("#content").fadeOut(100, function(){
        document.getElementById("content").innerHTML = "";
        var textnode = document.createTextNode("Jos uvijek nije dostupna forma za sifarnik.");
        document.getElementById("content").appendChild(textnode);
    });

    $("#content").fadeIn(500);
}

function generisiFormuZaNovogAdmina() {
    $("#content").fadeOut(100, function(){
    var content = document.getElementById("content");
    content.innerHTML = "";

    var prviRed = document.createElement("var");
    prviRed.classList.add("row", "wrapper--w680");
    var varIme = document.createElement("var");
    varIme.classList.add("col-2", "input-group");
    var ime = document.createTextNode("Ime");
    varIme.appendChild(ime);
    varIme.appendChild(document.createElement("br"));
    var txtIme = document.createElement('input');
    txtIme.type = 'text';
    txtIme.id = "ime";
    txtIme.classList.add("input--style-4");
    txtIme.style.height = "40px";
    txtIme.style.width = "250px";
    varIme.appendChild(txtIme);
    prviRed.appendChild(varIme);
    var varPrezime = document.createElement("var");
    varPrezime.classList.add("col-2", "input-group");
    var prezime = document.createTextNode("Prezime");
    varPrezime.appendChild(prezime);
    varPrezime.appendChild(document.createElement("br"));
    var txtPrezime = document.createElement('input');
    txtPrezime.type = 'text';
    txtPrezime.id = "prezime";
    txtPrezime.classList.add("input--style-4");
    txtPrezime.style.height = "40px"
    txtPrezime.style.width = "250px"
    content.appendChild(txtPrezime);
    varPrezime.appendChild(txtPrezime);
    prviRed.appendChild(varPrezime);
    content.appendChild(prviRed);

    var drugiRed = document.createElement("var");
    drugiRed.classList.add("row", "wrapper--w680");
    var varUsername = document.createElement("var");
    varUsername.classList.add("col-2", "input-group");
    var username = document.createTextNode("Korisničko ime");
    varUsername.appendChild(username);
    varUsername.appendChild(document.createElement("br"));
    var txtUsername = document.createElement('input');
    txtUsername.type = 'text';
    txtUsername.id = "username";
    txtUsername.classList.add("input--style-4");
    txtUsername.style.height = "40px"
    txtUsername.style.width = "250px"
    varUsername.appendChild(txtUsername);
    drugiRed.appendChild(varUsername);
    var varEmail = document.createElement("var");
    varEmail.classList.add("col-2", "input-group");
    var email = document.createTextNode("E-mail");
    varEmail.appendChild(email);
    varEmail.appendChild(document.createElement("br"));
    var txtEmail = document.createElement('input');
    txtEmail.type = 'email';
    txtEmail.id = "email";
    txtEmail.classList.add("input--style-4");
    txtEmail.style.height = "40px"
    txtEmail.style.width = "250px"
    varEmail.appendChild(txtEmail);
    drugiRed.appendChild(varEmail);
    content.appendChild(drugiRed);

    var treciRed = document.createElement("var");
    treciRed.classList.add("row", "wrapper--w680");
    var varLozinka = document.createElement("var");
    varLozinka.classList.add("col-2", "input-group");
    var lozinka = document.createTextNode("Lozinka");
    varLozinka.appendChild(lozinka);
    varLozinka.appendChild(document.createElement("br"));
    var txtLozinka = document.createElement('input');
    txtLozinka.type = 'password';
    txtLozinka.id = "lozinka";
    txtLozinka.classList.add("input--style-4");
    txtLozinka.style.height = "40px"
    txtLozinka.style.width = "250px"
    varLozinka.appendChild(txtLozinka);
    treciRed.appendChild(varLozinka);
    var varLozinka2 = document.createElement("var");
    varLozinka2.classList.add("col-2", "input-group");
    var lozinka2 = document.createTextNode("Ponovite lozinku");
    varLozinka2.appendChild(lozinka2);
    varLozinka2.appendChild(document.createElement("br"));
    var txtLozinka2 = document.createElement('input');
    txtLozinka2.type = 'password';
    txtLozinka2.id = "lozinka2";
    txtLozinka2.classList.add("input--style-4");
    txtLozinka2.style.height = "40px"
    txtLozinka2.style.width = "250px"
    varLozinka2.appendChild(txtLozinka2);
    treciRed.appendChild(varLozinka2);
    content.appendChild(treciRed);
    content.appendChild(document.createElement("br"));
    content.appendChild(document.createElement("br"));

    var btnAdd = document.createElement("BUTTON");
    btnAdd.classList.add("btn2", "btn--light-blue");
    btnAdd.innerHTML = "Dodaj";
    btnAdd.onclick = function(){
        var korisnickoIme=$('#username').val();
        var lozinka=$('#lozinka').val();
        var lozinka_potvrda=$('#lozinka2').val();
        var ime=$('#ime').val();
        var prezime=$('#prezime').val();
        var email=$('#email').val();

        if(korisnickoIme === "" || lozinka === "" || lozinka_potvrda === "" || ime === "" || prezime === "" || email === ""){
            alert("Nijedno polje ne sme ostati prazno!")
            return
        }

        if( lozinka!=lozinka_potvrda){
            alert("Lozinke se ne poklapaju!")
            return
        }

        if(lozinka.length < 5){
            alert("Lozinka mora imati više od pet karaktera!")
            return
        }

        if(ime.length < 3){
            alert("Ime mora imati bar tri karaktera!")
            return
        }

        if(korisnickoIme.length < 5){
            alert("Korisnicko ime mora imati bar pet karaktera!")
            return
        }

        if(prezime.length < 3){
            alert("Prezime mora imati bar tri karaktera!")
            return

        }

        $.post({
            url: 'api/adminKC/signup',
            data: JSON.stringify({korisnickoIme, lozinka, ime, prezime, email}),
            contentType: 'application/json',
            headers: {
                'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
            },
            success: function() {
                alert("Novi administrator uspjesno dodat.")
            },
            error: function() {
                alert("Novi administrator nije dodat.")
            }
        });
    }
    content.appendChild(btnAdd);
    });
    $("#content").fadeIn(500);
}

function logOut() {
    localStorage.removeItem('jwt');
    window.location = "index.html"
}