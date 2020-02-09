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

            var dobrodoslica = document.createTextNode("Dobro došli " + ulogovan.ime + "!");
            var span = document.createElement('span');
            span.style.fontSize = "20px";
            span.appendChild(dobrodoslica);
            document.getElementById("content").appendChild(span);

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
                            if(!ulogovan.promijenjenaLozinka){
                                promijeniLozinku(ulogovan);
                                break;
                            } else {
                                pocetnaAdminKlinike(ulogovan);
                                break;
                            }
                        case "ROLE_MEDICINSKA_SESTRA":
                            if(!ulogovan.promijenjenaLozinka){
                                promijeniLozinku(ulogovan);
                                break;
                            } else {
                                pocetnaMedicinskaSestra(ulogovan)
                                break;
                            }
                        case "ROLE_DOKTOR":
                            if(!ulogovan.promijenjenaLozinka){
                                promijeniLozinku(ulogovan);
                                break;
                            } else {
                                pocetnaLekar(ulogovan);
                                break;
                            }
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
        generisiKlinike([]);
    });
    $('body').on('click', '#istorijaBtn', function(e) {
        generisiIstoriju()
    });
    $('body').on('click', '#zdravstveniKartonBtn', function(e) {
        generisiZdravstveniKarton(ulogovan);
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
    //funkcije dugmadi za LEKARA
    $('body').on('click', '#listaPacijenataBtn', function(e) {
        generisiListuPacijenata();
    });
    $('body').on('click', '#zahtevGOBtn', function(e) {
        generisiFormuZaGOLekar(ulogovan);
    });
    $('body').on('click', '#zakaziBtn', function(e) {
        generisiFormuZaZakazivnje(ulogovan);
    });
    $('body').on('click', '#kalendarBtn', function(e) {
        generisiKalendarLekara(ulogovan);
    });
    //************************************************************************************************************************
    //funkcije dugmadi za ADMINA KLINIKE

    $('body').on('click', '#tipoviPregledaBtn', function(e) {
        generisiTipovePregleda();
    });

    $('body').on('click', '#zahteviBtn', function(e) {
        generisiZahteve();
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

    $('body').on('click', '#zahteviZaGoOdsBtn', function(e) {
        generisiZahteveZaGoOds();
    });
    //************************************************************************************************************************
    //funkcija profil-dugmeta
    $('body').on('click', '#profilBtn', function(e) {
        $.ajax
        ({
            type: "GET",
            url: 'api/korisnici/whoami',
            contentType: 'application/json',
            headers: {
                'Authorization': 'Bearer ' + token
            },
            success: function (ulogovan) {
                generisiProfil(ulogovan);
            }
        })
    });
    //funkcija za logOut
    $('body').on('click', '#logOutBtn', function(e) {
        logOut();
    });

});

function promijeniLozinku(ulogovan) {
    var modal = document.getElementById("requestModal");

    modal.style.display = "block";

    var span = document.getElementById("closeRequest");

    var p = document.getElementById("requestUsername");
    p.innerHTML = "";

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
        if (txtNovaLozinka.value === "" || txtNovaLozinka2.value == ""){
            alert("Nijedno polje ne sme ostati prazno!");
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

        var newPassword = txtNovaLozinka.value;

        $.post({
            url: 'api/korisnici/changePassFirstTime',
            headers: {
                'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
            },
            data: JSON.stringify({newPassword}),
            contentType: 'application/json',
            success: function(ulogovan) {
                alert("Lozinka uspješno promijenjena.")
                modal.style.display = "none";
                localStorage.setItem('ulogovan', JSON.stringify(ulogovan));
                alert("Potrebno je ponovno logovanje.");
                logOut();
            }
        });
    }
    p.append(btnPromijeni);
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
            var ocena = document.createTextNode("Ocena: " + korisnik.zbirOcena / korisnik.brojOcena);
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
            varOcena.appendChild(selectOcena);
            treciRed.appendChild(varOcena)

            content.appendChild(treciRed);

            /*********************************RADNO VRIJEME***************************************************/
            if(korisnik.radnoVreme != null){

                var pocRadnoVreme = korisnik.radnoVreme.split("-")[0];
                var krRadnoVreme = korisnik.radnoVreme.split("-")[1];

                var cetvrtiRed = document.createElement("var");
                cetvrtiRed.classList.add("row", "wrapper--w680");
                var varPocetak = document.createElement("var");
                varPocetak.classList.add("col-2", "input-group");
                var pocetak = document.createTextNode("Početak radnog vremena");
                varPocetak.appendChild(pocetak);
                varPocetak.appendChild(document.createElement("br"));
                var cifraPocetak = document.createElement('input');
                cifraPocetak.type = 'time';
                cifraPocetak.id = "pocRadnogVremena";
                cifraPocetak.classList.add("input--style-4");
                cifraPocetak.style.height = "40px"
                cifraPocetak.style.width = "250px"
                cifraPocetak.value = pocRadnoVreme;
                varPocetak.appendChild(cifraPocetak);
                cetvrtiRed.appendChild(varPocetak);
                var varKraj = document.createElement("var");
                varKraj.classList.add("col-2", "input-group");
                var lozinka2 = document.createTextNode("Ponovite lozinku");
                varKraj.appendChild(lozinka2);
                varKraj.appendChild(document.createElement("br"));
                var cifraKraj = document.createElement('input');
                cifraKraj.type = 'time';
                cifraKraj.id = "krRadnogVremena";
                cifraKraj.classList.add("input--style-4");
                cifraKraj.style.height = "40px"
                cifraKraj.style.width = "250px"
                cifraKraj.value = krRadnoVreme;
                varKraj.appendChild(cifraKraj);
                cetvrtiRed.appendChild(varKraj);

                content.appendChild(cetvrtiRed);
            }

            var sacuvajIzmjeneBtn = document.createElement("BUTTON");
            sacuvajIzmjeneBtn.classList.add("btn2", "btn--light-blue");
            sacuvajIzmjeneBtn.innerHTML = "Sacuvaj izmene";

            var obrisiBtn = document.createElement("BUTTON");
            obrisiBtn.classList.add("btn2", "btn--light-blue");
            obrisiBtn.innerHTML = "Obrisi korisnika";
            obrisiBtn.onclick = function () {
                $.ajax({
                    url: 'api/lekari/obrisiLekara/'+korisnik.id,
                    type: 'DELETE',
                    contentType: 'application/json',
                    headers: {
                        'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
                    },
                    success: function(obrisan) {
                        if(obrisan.id == null){
                            alert("Lekar ima zakazane preglede. Ne možete ga obrisati.");
                            return;
                        }
                        alert("Uspesno obrisan korisnik.");
                        window.location = "pocetna.html";
                    },
                    error: function() {
                        alert("Greska pri brisanju korisnika.");
                        window.location = "pocetna.html";

                    }
                });
            };

            //AKO JE LEKAR (ima ocjenu)
            if(korisnik.prosecnaOcena != null){
               content.appendChild(obrisiBtn);
            }

            sacuvajIzmjeneBtn.onclick = function(){
                let id = korisnik.id;
                let korisnickoIme = $('#username').val();
                let ime = $('#ime').val();
                let prezime = $('#prezime').val();
                let email = $('#email').val();
                let datumRodjenja = $('#datumRodjenja').val();
                let zvjezdice=$('#selectOcena').val();
                let prosecnaOcena = null;
                let poc = $('#pocRadnogVremena').val();
                let kr = $('#krRadnogVremena').val();
                let radnoVreme = poc + "-" + kr;
                console.log(radnoVreme)
                switch (zvjezdice) {
                    case "★★★★★":
                        prosecnaOcena = 5;
                        break;
                    case "★★★★":
                        prosecnaOcena = 4;
                        break;
                    case "★★★":
                        prosecnaOcena = 3;
                        break;
                    case "★★":
                        prosecnaOcena = 2;
                        break;
                    case "★":
                        prosecnaOcena = 1;
                }
                if(korisnickoIme == "" || ime == "" || prezime == "" || email == "" || datumRodjenja == "" || poc == "" || kr == ""){
                    alert("Nijedno polje ne sme ostati prazno.")
                    return;
                }

                $.post({
                    url: 'api/lekari/izmenaLekara',
                    data: JSON.stringify({id, korisnickoIme, ime, prezime, email, datumRodjenja, prosecnaOcena, radnoVreme}),
                    contentType: 'application/json',
                    headers: {
                        'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
                    },
                    success: function(lekar) {
                        if(lekar.id == null){
                            alert("Lekar ima zakazane preglede. Ne možete izvršiti izmenu.");
                            return;
                        }
                        alert("Uspesno izmenjen lekar.");
                    },
                    error: function() {
                        alert("Greska pri izmeni lekara.");
                    }
                });
            }
            content.appendChild(sacuvajIzmjeneBtn)

        });
        $("#content").fadeIn(500);
    }
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
        txtIme.classList.add("input--style-6");
        txtIme.style.height = "40px";
        txtIme.style.width = "250px";
        txtIme.value = korisnik.ime;
        //txtIme.disabled = "true";
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
        txtPrezime.classList.add("input--style-6");
        txtPrezime.style.height = "40px"
        txtPrezime.style.width = "250px"
        txtPrezime.value = korisnik.prezime;
        //txtPrezime.disabled = "true";
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
        txtUsername.classList.add("input--style-6");
        txtUsername.style.height = "40px"
        txtUsername.style.width = "250px"
        txtUsername.value = korisnik.username;
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
        txtDatumRodjenja.disabled = "true";
        varDatumRodjenja.appendChild(txtDatumRodjenja);
        treciRed.appendChild(varDatumRodjenja);
        var varJBO = document.createElement("var");
        varJBO.classList.add("col-2", "input-group");
        var jbo = document.createTextNode("JBZO");
        varJBO.appendChild(jbo);
        varJBO.appendChild(document.createElement("br"));
        var txtJBO = document.createElement('input');
        txtJBO.type = 'text';
        txtJBO.id = "jbo";
        txtJBO.classList.add("input--style-4");
        txtJBO.style.height = "40px"
        txtJBO.style.width = "250px"
        txtJBO.value = korisnik.jbo;
        txtJBO.disabled = "true";
        varJBO.appendChild(txtJBO);
        treciRed.appendChild(varJBO);
        content.appendChild(treciRed);

        var btnIzmene = document.createElement('btn');
        btnIzmene.classList.add("btn", "btn--radius-2", "btn--light-blue");
        btnIzmene.innerHTML = "Sacuvaj izmene";
        btnIzmene.id = "izmeneBtn";
        btnIzmene.onclick = function(){
            var ime = $('#ime').val();
            var prezime = $('#prezime').val();
            var email = $('#email').val();
            var korisnickoIme = $('#username').val();
            var id = korisnik.id;
            var stariUsername = korisnik.korisnickoIme;
            var jbo = korisnik.jbo;
            $.ajax({
                url:'api/korisnici/izmenaPodataka',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({id, ime, prezime, email, korisnickoIme, jbo}),
                headers: {
                    'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
                },
                success: function(korisnik) {
                    if(korisnik.korisnickoIme != stariUsername){
                        alert("Pri izmeni korisnickog imena, potrebno je ponovno logovanje.")
                        logOut();
                        return;
                    }
                    alert("Uspešno izmenjen profil.")
                    generisiProfil(korisnik);
                },
                error: function() {
                    alert("Greška pri izmeni profila.")
                }
            });
        }
        content.appendChild(document.createElement("br"));
        content.appendChild(document.createElement("br"));
        content.appendChild(btnIzmene);


        var btnChangePass = document.createElement("BUTTON");
        btnChangePass.classList.add("btn", "btn--radius-2", "btn--light-blue");
        btnChangePass.innerHTML = "Promeni lozinku";
        btnChangePass.onclick = function(){
            var modal = document.getElementById("requestModal");
            var p = document.getElementById("requestUsername");
            p.innerHTML = "";
            var staraLozinka = document.createTextNode("Stara lozinka");
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