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
            localStorage.setItem('ulogovan', JSON.stringify(ulogovan));
            console.log("IZ LOCAL STORAGE: " + localStorage.getItem('ulogovan'));
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

    $('body').on('click', '#tipoviPregledaBtn', function(e) {
        generisiTipovePregleda();
    });

    $('body').on('click', '#slobodniTerminiBtn', function(e) {
            var ulogovan = JSON.parse(localStorage.getItem('ulogovan'));
            $.ajax
            ({
                type: "GET",
                url: 'api/korisnici/getKlinikaAdmina/' + ulogovan.id,
                contentType: 'application/json',
                headers: {
                    'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
                },
                success: function (klinika) {
                    generisiSlobodneTermine(klinika);
                }
            });

    });
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
    var nazivi = ["Vasa klinika", "Dodaj salu", "Dodaj lekara", "Tipovi pregleda", imeKorisnika];

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
                btn.onclick = generisiKlinikuAdmina();
                break;
            case "Dodaj salu":
                btn.id = "dodajSaluBtn"
                btn.onclick = generisiFormuZaNovuSalu(ulogovan.klinika);
                break;
            case "Dodaj lekara":
                btn.id = "dodajLekaraBtn"
                btn.onclick = generisiFormuZaNovogLekara(ulogovan.klinika);
                break;
            case "Tipovi pregleda":
                btn.id = "tipoviPregledaBtn"
                //btn.onclick = generisiTipovePregleda(ulogovan.klinika);
                break;
            case imeKorisnika:
                btn.id = "profilBtn"
                break;

        }
    }

}
function generisiFormuZaNovuSalu(klinika) {
    return function () {
        $("#content").fadeOut(100, function(){
            var content = document.getElementById("content");
            content.innerHTML = "";

            var prviRed = document.createElement("var");
            prviRed.classList.add("row", "wrapper--w680");
            var varNaziv = document.createElement("var");
            varNaziv.classList.add("col-2", "input-group");
            var naziv = document.createTextNode("Naziv");
            varNaziv.appendChild(naziv);
            varNaziv.appendChild(document.createElement("br"));
            var txtNaziv = document.createElement('input');
            txtNaziv.type = 'text';
            txtNaziv.id = "nazivSale";
            txtNaziv.classList.add("input--style-4");
            txtNaziv.style.height = "40px";
            txtNaziv.style.width = "250px";
            varNaziv.appendChild(txtNaziv);
            prviRed.appendChild(varNaziv);

            var varDodaj = document.createElement("var");
            varDodaj.classList.add("col-2", "input-group");
            varDodaj.appendChild(document.createElement("br"));
            var btnAdd = document.createElement('button');
            btnAdd.classList.add("btn2", "btn--light-blue");
            btnAdd.style.height = "35px"
            btnAdd.style.width = "250px"
            btnAdd.innerHTML = "Dodaj salu";

            btnAdd.onclick = function(){
                var naziv=$('#nazivSale').val();

                if(naziv === ""){
                    alert("Polje ne sme ostati prazno.")
                    return
                }

                if(naziv.length < 3){
                    alert("Naziv mora imati više od tri karaktera!")
                    return
                }

                var idKlinike = klinika.id;
                console.log(localStorage.getItem('jwt'))
                $.post({
                    url: 'api/sale/dodajSalu',
                    data: JSON.stringify({naziv, idKlinike}),
                    contentType: 'application/json',
                    headers: {
                        'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
                    },
                    success: function() {
                        alert("Nova sala uspesno dodata.")
                    },
                    error: function() {
                        alert("Greška pri dodavanju sale.")
                    }
                });
            }
            varDodaj.appendChild(btnAdd);
            varDodaj.appendChild(document.createElement("br"));
            prviRed.appendChild(varDodaj);
            content.appendChild(prviRed);

        });
        $("#content").fadeIn(500);
    }
}

function generisiFormuZaNovogLekara(klinika) {
    return function () {
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
                var idKlinike = klinika.id;
                if(korisnickoIme === "" || lozinka === "" || lozinka_potvrda === "" || ime === "" || prezime === "" || email === ""){
                    alert("Nijedno polje ne sme ostati prazno.")
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
                    url: 'api/lekari/dodajLekara',
                    data: JSON.stringify({korisnickoIme, lozinka, ime, prezime, email, idKlinike}),
                    contentType: 'application/json',
                    headers: {
                        'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
                    },
                    success: function() {
                        alert("Novi lekar uspesno dodat.")
                    },
                    error: function() {
                        alert("Novi lekar nije dodat.")
                    }
                });
            }
            content.appendChild(btnAdd);
        });
        $("#content").fadeIn(500);
    }
}

function generisiTipovePregleda() {
    var ulogovan = JSON.parse(localStorage.getItem('ulogovan'));
        $.ajax
        ({
            type: "GET",
            url: 'api/korisnici/getKlinikaAdmina/' + ulogovan.id,
            contentType: 'application/json',
            headers: {
                'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
            },
            success: function (klinika) {
                $("#content").fadeOut(100, function () {
                    $.get({
                        url: 'api/klinike/getTipoviPregleda/' + klinika.id,
                        contentType: 'application/json',
                        headers: {
                            'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
                        },
                        success: function (tipoviPregleda) {
                            $("#content").fadeOut(100, function() {
                                var content = document.getElementById('content')
                                content.innerHTML = "";
                                var table = document.createElement('table');
                                table.classList.add("tabela");
                                var tableRef = document.createElement('tbody');

                                for(let tipPregleda of tipoviPregleda) {
                                    var podaciPregleda   = tableRef.insertRow();
                                    var nazivPregleda = podaciPregleda.insertCell(0);
                                    var nazivPregledaText = document.createTextNode(tipPregleda.naziv);
                                    nazivPregleda.appendChild(nazivPregledaText);

                                    var cenaPregleda = podaciPregleda.insertCell(1);
                                    var cenaPregledaText = document.createTextNode(tipPregleda.cena);
                                    cenaPregleda.appendChild(cenaPregledaText);

                                    var izmeni = podaciPregleda.insertCell(2);
                                    var izmeniBtn = document.createElement("BUTTON");
                                    izmeniBtn.classList.add("btn", "btn--radius-2", "btn--light-blue");
                                    izmeniBtn.innerHTML = "Izmeni";
                                    izmeniBtn.onclick = izmeniTipPregleda(tipPregleda);
                                    izmeni.appendChild(izmeniBtn);

                                    var ukloni = podaciPregleda.insertCell(3);
                                    var ukloniBtn = document.createElement("BUTTON");
                                    ukloniBtn.classList.add("btn", "btn--radius-2", "btn--light-blue");
                                    ukloniBtn.innerHTML = "-";
                                    ukloniBtn.onclick = ukloniTipPregleda(klinika.id, tipPregleda.id);
                                    ukloni.appendChild(ukloniBtn);

                                }
                                table.appendChild(tableRef);
                                content.appendChild(table);
                                var dodajBtn = document.createElement("BUTTON");
                                dodajBtn.classList.add("btn", "btn--radius-2", "btn--light-blue");
                                dodajBtn.innerHTML = "+";
                                dodajBtn.onclick = dodajTipPregleda(klinika.id);
                                content.appendChild(dodajBtn);
                            });
                            $("#content").fadeIn(500);
                        }, error: function () {
                            console.log("Greska")
                        }

                    });
                });
            }
        })
}

function izmeniTipPregleda(tipPregleda) {
    return function(){

        var modal = document.getElementById("tipPregledaModal");
        modal.style.display = "block";

        var span = document.getElementById("closeTipPregleda");

        span.onclick = function () {
            modal.style.display = "none";
        }

        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }

        var content = document.getElementById("tipPregledaDiv");
        content.innerHTML = "";

        var prviRed = document.createElement("var");
        prviRed.classList.add("row", "wrapper--w680");
        var varNaziv = document.createElement("var");
        varNaziv.classList.add("col-2", "input-group");
        var naziv = document.createTextNode("Naziv");
        varNaziv.appendChild(naziv);
        varNaziv.appendChild(document.createElement("br"));
        var txtNaziv = document.createElement('input');
        txtNaziv.type = 'text';
        txtNaziv.id = "naziv";
        txtNaziv.classList.add("input--style-4");
        txtNaziv.style.height = "40px";
        txtNaziv.style.width = "250px";
        txtNaziv.value = tipPregleda.naziv;
        varNaziv.appendChild(txtNaziv);
        prviRed.appendChild(varNaziv);

        var varCijena = document.createElement("var");
        varCijena.classList.add("col-2", "input-group");
        var cijena = document.createTextNode("Cena");
        varCijena.appendChild(cijena);
        varCijena.appendChild(document.createElement("br"));
        var cifra = document.createElement('input');
        cifra.type = 'number';
        cifra.id = "cena";
        //cifra.classList.add("input--style-4");
        cifra.style.height = "40px"
        cifra.style.width = "250px"
        cifra.value = tipPregleda.cena;
        content.appendChild(cifra);
        varCijena.appendChild(cifra);
        prviRed.appendChild(varCijena);
        content.appendChild(prviRed);

        var okBtn = document.createElement("BUTTON");
        okBtn.innerText = "OK"
        okBtn.classList.add("btn", "btn--radius-2", "btn--light-blue");

        content.appendChild(okBtn);
        okBtn.onclick = function(){

            id = tipPregleda.id;
            naziv =  $('#naziv').val();
            cena =  $('#cena').val();

            $.ajax({
                url:'api/klinike/izmeniTipPregleda',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({id, naziv, cena}),

                headers: {
                    'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
                },
                success: function() {
                    modal.style.display = "none";
                    generisiTipovePregleda();
                }
            });
        }

    }
}


function ukloniTipPregleda(klinikaId, tipPregledaId) {
    return function(){

        $.ajax({
            url:'api/klinike/deleteTipPregleda/'+klinikaId+'/'+tipPregledaId,
            type: 'DELETE',
            headers: {
                'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
            },
            success: function() {
                generisiTipovePregleda();
            }
        });
    }
}



function dodajTipPregleda(idKlinike) {
    return function(){

        var modal = document.getElementById("tipPregledaModal");
        modal.style.display = "block";

        var span = document.getElementById("closeTipPregleda");

        span.onclick = function () {
            modal.style.display = "none";
        }

        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }

        var content = document.getElementById("tipPregledaDiv");
        content.innerHTML = "";

        var prviRed = document.createElement("var");
        prviRed.classList.add("row", "wrapper--w680");
        var varNaziv = document.createElement("var");
        varNaziv.classList.add("col-2", "input-group");
        var naziv = document.createTextNode("Naziv");
        varNaziv.appendChild(naziv);
        varNaziv.appendChild(document.createElement("br"));
        var txtNaziv = document.createElement('input');
        txtNaziv.type = 'text';
        txtNaziv.id = "naziv";
        txtNaziv.classList.add("input--style-4");
        txtNaziv.style.height = "40px";
        txtNaziv.style.width = "250px";
        varNaziv.appendChild(txtNaziv);
        prviRed.appendChild(varNaziv);

        var varCijena = document.createElement("var");
        varCijena.classList.add("col-2", "input-group");
        var cijena = document.createTextNode("Cena");
        varCijena.appendChild(cijena);
        varCijena.appendChild(document.createElement("br"));
        var cifra = document.createElement('input');
        cifra.type = 'number';
        cifra.id = "cena";
        cifra.style.height = "40px"
        cifra.style.width = "250px"
        content.appendChild(cifra);
        varCijena.appendChild(cifra);
        prviRed.appendChild(varCijena);
        content.appendChild(prviRed);


        var treciRed = document.createElement("var");
        treciRed.classList.add("row", "wrapper--w680");

        var varOk = document.createElement("var");
        varOk.classList.add("col-2", "input-group");

        var okBtn = document.createElement("BUTTON");
        okBtn.innerText = "OK"
        okBtn.classList.add("btn", "btn--radius-2", "btn--light-blue");

        varOk.appendChild(okBtn);
        treciRed.appendChild(varOk);
        content.appendChild(treciRed);

        okBtn.onclick = function(){
            naziv =  $('#naziv').val();
            cena =  $('#cena').val();

            if(naziv == "" || cena == 0){
                alert("Popunite sva polja!");
                return;
            }

            $.ajax({
                url:'api/klinike/dodajTipPregleda',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({idKlinike, naziv, cena}),

                headers: {
                    'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
                },
                success: function() {
                    modal.style.display = "none";
                    generisiTipovePregleda();
                }
            });
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

function generisiKlinikuAdmina() {
    var ulogovan = JSON.parse(localStorage.getItem('ulogovan'));
    return function(){
        $.ajax
        ({
            type: "GET",
            url: 'api/korisnici/getKlinikaAdmina/'+ulogovan.id,
            contentType: 'application/json',
            headers: {
                'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
            },
            success: function (klinika){
                $("#content").fadeOut(100, function() {
                    var content = document.getElementById("content");
                    content.innerHTML = "";

                    var prviRed = document.createElement("var");
                    prviRed.classList.add("row", "wrapper--w680");
                    var varNaziv = document.createElement("var");
                    varNaziv.classList.add("col-2", "input-group");
                    var naziv = document.createTextNode("Naziv");
                    varNaziv.appendChild(naziv);
                    varNaziv.appendChild(document.createElement("br"));
                    var txtNaziv = document.createElement('input');
                    txtNaziv.type = 'text';
                    txtNaziv.id = "naziv";
                    txtNaziv.classList.add("input--style-4");
                    txtNaziv.style.height = "40px";
                    txtNaziv.style.width = "250px";
                    txtNaziv.value = klinika.naziv;
                    varNaziv.appendChild(txtNaziv);
                    prviRed.appendChild(varNaziv);

                    var varLokacija = document.createElement("var");
                    varLokacija.classList.add("col-2", "input-group");
                    var lokacija = document.createTextNode("Lokacija");
                    varLokacija.appendChild(lokacija);
                    varLokacija.appendChild(document.createElement("br"));
                    var txtLokacija = document.createElement('input');
                    txtLokacija.type = 'text';
                    txtLokacija.id = "lokacija";
                    txtLokacija.classList.add("input--style-4");
                    txtLokacija.style.height = "40px"
                    txtLokacija.style.width = "250px"
                    txtLokacija.value = klinika.lokacija;
                    content.appendChild(txtLokacija);
                    varLokacija.appendChild(txtLokacija);
                    prviRed.appendChild(varLokacija);
                    content.appendChild(prviRed);

                    var drugiRed = document.createElement("var");
                    drugiRed.classList.add("row", "wrapper--w680");
                    var varOpis = document.createElement("var");
                    varOpis.classList.add("col-2", "input-group");
                    var opis = document.createTextNode("Opis");
                    varOpis.appendChild(opis);
                    varOpis.appendChild(document.createElement("br"));
                    var txtOpis = document.createElement('input');
                    txtOpis.type = 'text';
                    txtOpis.id = "opis";
                    txtOpis.classList.add("input--style-4");
                    txtOpis.style.height = "45px"
                    txtOpis.style.width = "565px"
                    txtOpis.style.marginLeft = "27px"
                    txtOpis.value = klinika.opis;
                    varOpis.appendChild(txtOpis);
                    drugiRed.appendChild(varOpis);
                    content.appendChild(drugiRed);

                    var treciRed = document.createElement("var");
                    treciRed.classList.add("row", "wrapper--w680");
                    var btnLekari = document.createElement('btn');
                    btnLekari.classList.add("btn", "btn--radius-2", "btn--light-blue");
                    btnLekari.innerHTML = "Lekari";
                    btnLekari.id = "lekariBtn";
                    btnLekari.style.width = "250px"
                    btnLekari.style.marginLeft = "27px"
                    btnLekari.style.marginTop = "10px"
                    btnLekari.onclick = prikaziLekareKlinike(klinika);

                    var btnSale = document.createElement('btn');
                    btnSale.classList.add("btn", "btn--radius-2", "btn--light-blue");
                    btnSale.innerHTML = "Sale";
                    btnSale.id = "saleBtn";
                    btnSale.style.marginTop = "10px"
                    btnSale.style.marginLeft = "57px"
                    btnSale.style.width = "250px"
                    btnSale.onclick = prikaziSaleKlinike(klinika);
                    treciRed.appendChild(btnLekari);
                    treciRed.appendChild(btnSale);
                    content.appendChild(treciRed);

                    /*var btnTermini = document.createElement('btn');
                    btnTermini.classList.add("btn", "btn--radius-2", "btn--light-blue");
                    btnTermini.innerHTML = "Slobodni termini";
                    btnTermini.id = "slobodniTerminiBtn";
                    //btnTermini.onclick = generisiSlobodneTermine(klinika);
                    content.appendChild(document.createElement("br"));
                    content.appendChild(document.createElement("br"));
                    content.appendChild(btnTermini);*/

                    //****************************************************
                    var cetvrtiRed = document.createElement("var");
                    cetvrtiRed.classList.add("row", "wrapper--w680");
                    var btnTermini = document.createElement('btn');
                    btnTermini.classList.add("btn", "btn--radius-2", "btn--light-blue");
                    btnTermini.innerHTML = "Slobodni termini";
                    btnTermini.id = "slobodniTerminiBtn";
                    btnTermini.style.width = "250px"
                    btnTermini.style.marginLeft = "27px"
                    btnTermini.style.marginTop = "10px"

                    var btnIzvestaj = document.createElement('btn');
                    btnIzvestaj.classList.add("btn", "btn--radius-2", "btn--light-blue");
                    btnIzvestaj.innerHTML = "Izvestaj";
                    btnIzvestaj.id = "izvestajBtn";
                    btnIzvestaj.style.marginTop = "10px"
                    btnIzvestaj.style.marginLeft = "57px"
                    btnIzvestaj.style.width = "250px"
                    btnIzvestaj.onclick = prikaziIzvestajPoslovanja(klinika);
                    cetvrtiRed.appendChild(btnTermini);
                    cetvrtiRed.appendChild(btnIzvestaj);
                    content.appendChild(cetvrtiRed);
                    //******************************************

                    var btnIzmene = document.createElement('btn');
                    btnIzmene.classList.add("btn", "btn--radius-2", "btn--light-blue");
                    btnIzmene.innerHTML = "Sacuvaj izmene";
                    btnIzmene.id = "izmeneBtn";
                    btnIzmene.onclick = function(){
                        var id = klinika.id;

                        var naziv = $('#naziv').val();
                        var opis = $('#opis').val();
                        var lokacija = $('#lokacija').val();
                        $.ajax({
                            url:'api/klinike/izmenaKlinike',
                            type: 'POST',
                            contentType: 'application/json',
                            data: JSON.stringify({id, naziv, opis, lokacija}),
                            headers: {
                                'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
                            },
                            success: function() {
                                alert("Uspešno izmenjena klinika.")

                            },
                            error: function() {
                                alert("Greška pri izmeni klinike.")
                            }
                        });
                    }
                    content.appendChild(document.createElement("br"));
                    content.appendChild(document.createElement("br"));
                    content.appendChild(btnIzmene);

                });
                $("#content").fadeIn(500);
            },
            error: function () {
                alert("Greska pri dobavljanju informacija o klinici.")
            }
        });


    }
}

function prikaziIzvestajPoslovanja(klinika) {
    return function () {
        $("#content").fadeOut(100, function(){
            var content = document.getElementById("content");
            content.innerHTML = "";

            var prviRed = document.createElement("var");
            prviRed.classList.add("row", "wrapper--w680");

            var varNaziv = document.createElement("var");
            varNaziv.classList.add("col-2", "input-group");
            var naziv = document.createTextNode("Naziv klinike: " + klinika.naziv);
            varNaziv.appendChild(naziv);
            varNaziv.appendChild(document.createElement("br"));
            prviRed.appendChild(varNaziv)

            var varOcena = document.createElement("var");
            varOcena.classList.add("col-2", "input-group");
            var ocena = document.createTextNode("Prosecna ocena klinike: " + klinika.prosecnaOcena);
            varOcena.appendChild(ocena);
            varOcena.appendChild(document.createElement("br"));
            prviRed.appendChild(varOcena);

            var drugiRed = document.createElement("var");
            drugiRed.classList.add("row", "wrapper--w680");

            var table = document.createElement('table');
            table.classList.add("tabela");
            var tableRef = document.createElement('tbody');

            var nasloviRed = tableRef.insertRow();
            var lekarNaslov = nasloviRed.insertCell(0);
            var lekarNaslovText = document.createTextNode("Ime i prezime lekara");
            lekarNaslov.appendChild(lekarNaslovText);

            var ocenaNaslov = nasloviRed.insertCell(1);
            var ocenaNaslovText = document.createTextNode("Prosecna ocena lekara");
            ocenaNaslov.appendChild(ocenaNaslovText);

            for (let lekar of klinika.lekari) {
                var lekarRed = tableRef.insertRow();
                var naziv = lekarRed.insertCell(0);
                var nazivText = document.createTextNode(lekar.ime + " " + lekar.prezime);
                naziv.appendChild(nazivText);

                var ocenaLekara = lekarRed.insertCell(1);
                var ocenaLekaraText = document.createTextNode(lekar.ocena);
                ocenaLekara.appendChild(ocenaLekaraText);
            }
            table.appendChild(tableRef);
            drugiRed.appendChild(table);

            var treciRed = document.createElement("var");
            treciRed.classList.add("row", "wrapper--w680");

            var varPocDatum = document.createElement("var");
            varPocDatum.classList.add("col-2", "input-group");
            var datumTxt = document.createTextNode("Pocetni datum prihoda klinike");
            varPocDatum.style.marginTop = "20px";
            varPocDatum.appendChild(datumTxt);
            varPocDatum.appendChild(document.createElement("br"));
            var datumPocetak = document.createElement('input');
            datumPocetak.type = 'date';
            datumPocetak.id = "datumPocetakPrihoda";
            datumPocetak.classList.add("input--style-4");
            datumPocetak.style.height = "40px";
            datumPocetak.style.width = "250px"
            datumPocetak.onchange = prihodiKlinike(klinika);
            varPocDatum.appendChild(datumPocetak);
            treciRed.appendChild(varPocDatum);

            var varKrajDatum = document.createElement("var");
            varKrajDatum.classList.add("col-2", "input-group");
            var datumTxt = document.createTextNode("Krajnji datum prihoda klinike");
            varKrajDatum.style.marginTop = "20px";
            varKrajDatum.appendChild(datumTxt);
            varKrajDatum.appendChild(document.createElement("br"));
            var datumKraj = document.createElement('input');
            datumKraj.type = 'date';
            datumKraj.id = "datumKrajPrihoda";
            datumKraj.classList.add("input--style-4");
            datumKraj.style.height = "40px";
            datumKraj.style.width = "250px"
            datumKraj.onchange = prihodiKlinike(klinika);
            varKrajDatum.appendChild(datumKraj);
            treciRed.appendChild(varKrajDatum);

            var varPrihodi = document.createElement("var");
            varPrihodi.classList.add("col-2", "input-group");
            var prihodi = document.createElement('input');
            prihodi.type = 'number';
            prihodi.disabled = true;
            prihodi.id = "prihodi";
            prihodi.classList.add("input--style-4");
            prihodi.style.height = "40px";
            prihodi.style.width = "250px"
            varPrihodi.appendChild(prihodi);

            var odrzaniPreglediBtn = document.createElement("BUTTON");
            odrzaniPreglediBtn.classList.add("btn-list", "btn--radius-2", "btn--light-blue");
            odrzaniPreglediBtn.innerHTML = "Održani pregledi";
            odrzaniPreglediBtn.style.marginTop = "20px"
            odrzaniPreglediBtn.id = "odrzaniPreglediBtn";
            //odrzaniPreglediBtn.onclick = prikazOdrzanihPregleda(klinika);

            content.appendChild(prviRed);
            content.appendChild(drugiRed);
            content.appendChild(treciRed);
            content.appendChild(varPrihodi);
            content.appendChild(odrzaniPreglediBtn);

        });

        $("#content").fadeIn(500);
    }
}

function prihodiKlinike(klinika) {
    return function(){
        var poc = $("#datumPocetakPrihoda").val();
        var kr = $("#datumKrajPrihoda").val();
        if( poc == "" || kr == ""){
            return;
        } else if(kr < poc){
            alert("Krajnji datum mora biti veći od početnog.")
            return;
        }

        var prihodi = 0;
        for( let pregled of klinika.obavljeniPregledi){
            if(pregled.termin.pocetak > poc && pregled.termin.kraj < kr){
                prihodi = prihodi + pregled.tipPregleda.cena;
            }
        }
        var polje = $("#prihodi");
        polje.val(prihodi);
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


function infoSale(sala)
{
    return function() {
        var modal = document.getElementById("saleModal");
        modal.style.display = "none";
        $("#content").fadeOut(100, function(){

            $.get({

                url:'api/sale/getTermini/'+sala.id,
                contentType: 'application/json',
                headers: {
                    'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
                },
                success: function(termini)
                {
                    var content = document.getElementById("content")
                    content.innerHTML = "";

                    var prviRed = document.createElement("var");
                    prviRed.classList.add("row", "wrapper--w680");

                    var varNaziv = document.createElement("var");
                    varNaziv.classList.add("col-2", "input-group");
                    var naziv = document.createTextNode("Naziv");
                    varNaziv.appendChild(naziv);
                    varNaziv.appendChild(document.createElement("br"));
                    var txtNaziv = document.createElement('input');
                    txtNaziv.type = 'text';
                    txtNaziv.id = "nazivSale";
                    txtNaziv.classList.add("input--style-4");
                    txtNaziv.style.height = "40px";
                    txtNaziv.style.width = "250px";
                    txtNaziv.value = sala.naziv;
                    if(sala.slobodna != true)
                        txtNaziv.disabled = "true";
                    varNaziv.appendChild(txtNaziv);
                    prviRed.appendChild(varNaziv);

                    var varObrisi = document.createElement("var");
                    varObrisi.classList.add("col-2", "input-group");
                    varObrisi.appendChild(document.createElement("br"));
                    var btnObrisi = document.createElement('button');
                    btnObrisi.classList.add("btn2", "btn--light-blue");
                    btnObrisi.style.height = "35px"
                    btnObrisi.style.width = "250px"
                    btnObrisi.innerHTML = "Obrisi salu";

                    btnObrisi.onclick = function(){
                        if(sala.slobodna == false ||sala.rezervisana == true){
                            alert("Nemoguće je trenutno obrisati salu!");
                            return;
                        }
                        $.ajax({
                            url:'api/sale/'+sala.id,
                            type: 'DELETE',
                            headers: {
                                'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
                            },
                            success: function() {
                               window.location = "pocetna.html"
                            }
                        });
                    }
                    varObrisi.appendChild(btnObrisi);
                    varObrisi.appendChild(document.createElement("br"));
                    prviRed.appendChild(varObrisi);
                    content.appendChild(prviRed);

                    var drugiRed = document.createElement("var");
                    drugiRed.classList.add("row", "wrapper--w680");
                    var varRezervacije = document.createElement("var");
                    varRezervacije.classList.add("col-2", "input-group");
                    var rezervacije = document.createTextNode("Rezervacije");
                    varRezervacije.appendChild(rezervacije);
                    varRezervacije.appendChild(document.createElement("br"));
                    // Make a container element for the list
                    var listContainer = document.createElement('div')
                    // Make the list
                    var listElement = document.createElement('ul')
                    // Add it to the page
                    varRezervacije.appendChild(listContainer);
                    listContainer.appendChild(listElement);

                    for (let termin of termini) {
                        // create an item for each one
                        var listItem = document.createElement('li');
                        // Add the item text
                        listItem.innerHTML = termin.pocetak + " - " +termin.kraj;
                        // Add listItem to the listElement
                        listElement.appendChild(listItem);
                    }

                    drugiRed.appendChild(varRezervacije);
                    content.appendChild(drugiRed);

                    var sacuvajIzmjene = document.createElement("var");
                    sacuvajIzmjene.classList.add("col-2", "input-group");
                    sacuvajIzmjene.appendChild(document.createElement("br"));
                    var sacuvajIzmjeneBtn = document.createElement("BUTTON");
                    sacuvajIzmjeneBtn.classList.add("btn2", "btn--light-blue");
                    sacuvajIzmjeneBtn.innerHTML = "Sacuvaj izmjene";
                    sacuvajIzmjeneBtn.onclick = function(){
                        if(sala.slobodna == false ||sala.rezervisana == true){
                            alert("Nemoguće je trenutno izmeniti salu!");
                            return;
                        }
                        var id = sala.id;
                        var naziv = $('#nazivSale').val();
                        $.ajax({
                            url:'api/sale/izmenaSale',
                            type: 'POST',
                            contentType: 'application/json',
                            data: JSON.stringify({id, naziv}),
                            headers: {
                                'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
                            },
                            success: function() {
                                alert("Uspešno izmenjena sala.")
                            },
                            error: function() {
                                alert("Greška pri izmeni sale.")
                            }
                        });
                    }
                    sacuvajIzmjene.appendChild(sacuvajIzmjeneBtn);
                    sacuvajIzmjene.appendChild(document.createElement("br"));
                    drugiRed.appendChild(sacuvajIzmjene);
                    content.appendChild(drugiRed);
                }

            });
        });
        $("#content").fadeIn(500);
    }
}


function generisiSlobodneTermine(klinika) {
        $.get({

            url: 'api/klinike/getSlobodniTermini/' + klinika.naziv,
            contentType: 'application/json',
            headers: {
                'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
            },
            success: function (termini) {
                $("#content").fadeOut(100, function () {
                    var content = document.getElementById("content")
                    content.innerHTML = "";

                    var table = document.createElement('table');
                    table.classList.add("tabela");
                    var tableRef = document.createElement('tbody');

                    for (let termin of termini) {
                        var podaciTermina = tableRef.insertRow();
                        var pocetakTermina = podaciTermina.insertCell(0);
                        var pocetakTerminaText = document.createTextNode(termin.pocetak);
                        pocetakTermina.appendChild(pocetakTerminaText);

                        var krajTermina = podaciTermina.insertCell(1);
                        var krajTerminaText = document.createTextNode(termin.kraj);
                        krajTermina.appendChild(krajTerminaText);

                        var ukloni = podaciTermina.insertCell(2);
                        var ukloniBtn = document.createElement("BUTTON");
                        ukloniBtn.classList.add("btn", "btn--radius-2", "btn--light-blue");
                        ukloniBtn.innerHTML = "-";
                        ukloniBtn.onclick = ukloniTermin(klinika, termin.id);
                        ukloni.appendChild(ukloniBtn);

                    }
                    table.appendChild(tableRef);
                    content.appendChild(table);
                    var dodajBtn = document.createElement("BUTTON");
                    dodajBtn.classList.add("btn", "btn--radius-2", "btn--light-blue");
                    dodajBtn.innerHTML = "+";
                    dodajBtn.onclick = dodajSlobodanTermin(klinika);
                    content.appendChild(dodajBtn);
                });
                $("#content").fadeIn(500);
            }

        });

}
function dodajSlobodanTermin(klinika) {
    return function () {
        var modal = document.getElementById("slobodanTerminModal");
        modal.style.display = "block";

        var span = document.getElementById("closeTermini");

        span.onclick = function () {
            modal.style.display = "none";
        }

        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }

        var content = document.getElementById("slobodniTerminiDiv");
        content.innerHTML = "";

        var nultiRed = document.createElement("var");
        nultiRed.classList.add("row", "wrapper--w680");
        var varDatum = document.createElement("var");
        varDatum.classList.add("col-2", "input-group");
        var datumTxt = document.createTextNode("Datum");
        varDatum.appendChild(datumTxt);
        varDatum.appendChild(document.createElement("br"));
        var datum = document.createElement('input');
        datum.type = 'date';
        datum.id = "datum";
        datum.classList.add("input--style-4");
        datum.style.height = "40px";
        datum.style.width = "250px"
        varDatum.appendChild(datum);
        nultiRed.appendChild(varDatum);

        content.appendChild(nultiRed);

        var prviRed = document.createElement("var");
        prviRed.classList.add("row", "wrapper--w680");
        var varPocetak = document.createElement("var");
        varPocetak.classList.add("col-2", "input-group");
        var pocetakTxt = document.createTextNode("Pocetak");
        varPocetak.appendChild(pocetakTxt);
        varPocetak.appendChild(document.createElement("br"));
        var pocetak = document.createElement('input');
        pocetak.type = 'time';
        pocetak.id = "pocetak";
        pocetak.classList.add("input--style-4");
        pocetak.style.height = "40px";
        pocetak.style.width = "250px"
        varPocetak.appendChild(pocetak);
        prviRed.appendChild(varPocetak);

        var varKraj = document.createElement("var");
        varKraj.classList.add("col-2", "input-group");
        var krajTxt = document.createTextNode("Kraj");
        varKraj.appendChild(krajTxt);
        varKraj.appendChild(document.createElement("br"));
        var kraj = document.createElement('input');
        kraj.type = 'time';
        kraj.id = "kraj";
        kraj.classList.add("input--style-4");
        kraj.style.height = "40px";
        kraj.style.width = "250px"
        varKraj.appendChild(kraj);
        prviRed.appendChild(varKraj);

        content.appendChild(prviRed);

        var drugiRed = document.createElement("var");
        drugiRed.classList.add("row", "wrapper--w680");

        var varOk = document.createElement("var");
        varOk.classList.add("col-2", "input-group");

        var okBtn = document.createElement("BUTTON");
        okBtn.innerText = "OK"
        okBtn.classList.add("btn", "btn--radius-2", "btn--light-blue");

        varOk.appendChild(okBtn);
        drugiRed.appendChild(varOk);
        content.appendChild(drugiRed);

        okBtn.onclick = function(){
            datum = $('#datum').val();
            pocetak =  $('#pocetak').val();
            kraj =  $('#kraj').val();

            if(pocetak == "" || kraj == "" || datum == ""){
                alert("Popunite sva polja!");
                return;
            }

            pocetak = datum + " "  + pocetak +":00";
            kraj = datum + " "  + kraj +":00";


            $.ajax({
                url:'api/klinike/'+klinika.id+'/dodajSlobodanTermin',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({pocetak, kraj}),

                headers: {
                    'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
                },
                success: function() {
                    modal.style.display = "none";
                    generisiSlobodneTermine(klinika);
                }
            });
        }


    }
}

function ukloniTermin(klinika, terminId) {
    return function(){
        $.ajax({
            url:'api/klinike/deleteTermin/'+klinika.id+'/'+terminId,
            type: 'GET',
            headers: {
                'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
            },
            success: function() {
                generisiSlobodneTermine(klinika);
            }
        });
    }
}

function prikaziSaleKlinike(klinika) {
    return function(){
        $.get({

            url: 'api/klinike/getSale/' + klinika.naziv,
            contentType: 'application/json',
            headers: {
                'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
            },
            success: function (sale) {
                var modal = document.getElementById("saleModal");
                modal.style.display = "block";

                var span = document.getElementById("closeSale");

                span.onclick = function () {
                    modal.style.display = "none";
                }

                window.onclick = function (event) {
                    if (event.target == modal) {
                        modal.style.display = "none";
                    }
                }
                var saleDiv = document.getElementById("saleDiv");
                saleDiv.innerHTML = "";
                if(sale.length > 0){
                    for (let sala of sale) {
                        var btn = document.createElement("BUTTON");
                        btn.classList.add("btn-list", "btn--radius-2", "btn--light-blue");
                        btn.innerHTML = sala.naziv;
                        btn.id = sala.naziv;
                        btn.onclick = infoSale(sala);
                        saleDiv.appendChild(btn);
                    }
                } else if (sale.length === 0) {
                    var textnode = document.createTextNode("Ne postoje sale u klinici.");
                    document.getElementById("saleDiv").appendChild(textnode);
                }
            }

        });
    }
}

function prikaziLekareKlinike(klinika) {
    return function(){
        $.get({

            url:'api/klinike/getLekari/'+klinika.naziv,
            contentType: 'application/json',
            headers: {
                'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
            },
            success: function(lekari)
            {
                var modal = document.getElementById("lekariModal");
                modal.style.display = "block";

                var span = document.getElementById("closeLekari");

                span.onclick = function() {
                    modal.style.display = "none";
                }

                window.onclick = function(event) {
                    if (event.target == modal) {
                        modal.style.display = "none";
                    }
                }

                var lekariDiv = document.getElementById("lekariDiv");
                lekariDiv.innerHTML = "";
                for(let lekar of lekari)
                {
                    console.log(lekar)
                    var btn = document.createElement("BUTTON");
                    btn.classList.add("btn-list", "btn--radius-2", "btn--light-blue");
                    btn.innerHTML = lekar.ime + " " + lekar.prezime;
                    btn.onclick = prikazKorisnika(lekar);
                    lekariDiv.appendChild(btn);
                }

            }

        });
    }
}
function prikazKorisnika(korisnik) {
    return function () {
        var modal = document.getElementById("lekariModal");
        modal.style.display = "none";
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
            txtUsername.value = korisnik.korisnickoIme;
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
            varEmail.appendChild(txtEmail);
            drugiRed.appendChild(varEmail);
            content.appendChild(drugiRed);

            var treciRed = document.createElement("var");
            treciRed.classList.add("row", "wrapper--w680");
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

            varDatumRodjenja.appendChild(txtDatumRodjenja);
            treciRed.appendChild(varDatumRodjenja)

            //***************************************ZA LEKARA*************************************
            var varOcena = document.createElement("var");
            varOcena.classList.add("col-2", "input-group");
            var ocena = document.createTextNode("Ocena: " + korisnik.ocena);
            varOcena.appendChild(ocena);
            varOcena.appendChild(document.createElement("br"));
            var selectOcena = document.createElement('select');
            //zvjezdice
            var array = ["★★★★★", "★★★★", "★★★", "★★", "★"];
            selectOcena.id = "selectOcena";
            //Create and append the options
            for (var i = 0; i < array.length; i++) {
                var option = document.createElement("option");
                option.value = array[i];
                option.text = array[i];
                selectOcena.appendChild(option);
            }
            //console.log(JSON.parse(korisnik))
            if(korisnik.ocena != null){
                varOcena.appendChild(selectOcena);
                treciRed.appendChild(varOcena)
            }
            content.appendChild(treciRed);

            if(korisnik.slobodan != null && korisnik.slobodan){
                console.log("SLOBODAN")
            } else if(!korisnik.slobodan){
                selectOcena.disabled = "true";
                txtDatumRodjenja.disabled = "true";
                txtEmail.disabled = "true";
                txtUsername.disabled = "true";
                txtPrezime.disabled = "true";
                txtIme.disabled = "true";
                selectOcena.disabled = "true";
            }
            //***************************************************************************************

            var sacuvajIzmjeneBtn = document.createElement("BUTTON");
            sacuvajIzmjeneBtn.classList.add("btn2", "btn--light-blue");
            sacuvajIzmjeneBtn.innerHTML = "Sacuvaj izmene";

            sacuvajIzmjeneBtn.onclick = function(){
                let id = korisnik.id;
                let korisnickoIme = $('#username').val();
                let ime = $('#ime').val();
                let prezime = $('#prezime').val();
                let email = $('#email').val();
                let datumRodjenja = $('#datumRodjenja').val();
                let zvjezdice=$('#selectOcena').val();
                let ocena = null;
                switch (zvjezdice) {
                    case "★★★★★":
                        ocena = 5;
                        break;
                    case "★★★★":
                        ocena = 4;
                        break;
                    case "★★★":
                        ocena = 3;
                        break;
                    case "★★":
                        ocena = 2;
                        break;
                    case "★":
                        ocena = 1;
                }
                if(korisnickoIme == "" || ime == "" || prezime == "" || email == "" || datumRodjenja == ""){
                    alert("Nijedno polje ne sme ostati prazno.")
                    return;
                }

                console.log(ocena)
                $.post({
                    url: 'api/lekari/izmenaLekara',
                    data: JSON.stringify({id, korisnickoIme, ime, prezime, email, datumRodjenja, ocena}),
                    contentType: 'application/json',
                    headers: {
                        'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
                    },
                    success: function() {
                        alert("Uspesno izmenjen lekar.")
                    },
                    error: function() {
                        alert("Greska pri izmeni lekara.")
                    }
                });
            }
            content.appendChild(sacuvajIzmjeneBtn)

        });
        $("#content").fadeIn(500);
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