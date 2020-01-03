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
                btn.onclick = generisiKlinikuAdmina(ulogovan.klinika);
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
                btn.onclick = generisiTipovePregleda(ulogovan.klinika);
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

function generisiTipovePregleda(klinika) {
    return function () {

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

            /*
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
            drugiRed.appendChild(varEmail);*/



        });
        $("#content").fadeIn(500);
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