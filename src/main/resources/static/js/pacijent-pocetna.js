

function pocetnaPacijent(ulogovan) {
    korisnik = ulogovan;
    var imeKorisnika = korisnik.ime + " " + korisnik.prezime;
    var nazivi = ["Klinike", "Vaša istorija", "Zdravstveni karton", imeKorisnika];

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
            case "Vaša istorija":
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

function generisiIstoriju() {
    $("#content").fadeOut(100, function(){
        document.getElementById("content").innerHTML = "";

        var submit = document.createElement("BUTTON");
        submit.classList.add("btn", "btn--radius-2", "btn--light-blue");
        submit.innerHTML = "Oceni lekara";
        submit.id = "oceniLekara";
        submit.onclick = generisiOcjenjivanjeLekara();
        document.getElementById("content").appendChild(submit);

        var submitK = document.createElement("BUTTON");
        submitK.classList.add("btn", "btn--radius-2", "btn--light-blue");
        submitK.innerHTML = "Oceni kliniku";
        submitK.id = "oceniLekara";
        submitK.onclick = generisiOcjenjivanjeKlinike();
        document.getElementById("content").appendChild(submitK);

        var lblPregledi = document.createElement("H1");
        lblPregledi.appendChild(document.createTextNode("Pregledi"));
        document.getElementById("content").appendChild(lblPregledi);

        var txtP1 = document.createElement('input');
        txtP1.disabled = 'true';
        txtP1.value = "Tip pregleda";
        txtP1.style.height = "40px";
        txtP1.style.width = "200px";
        txtP1.style.textAlign = 'center';
        document.getElementById("content").appendChild(txtP1);
        var txtP2 = document.createElement('input');
        txtP2.disabled = 'true';
        txtP2.value = "Lekar";
        txtP2.style.height = "40px";
        txtP2.style.width = "200px";
        txtP2.style.textAlign = 'center';
        document.getElementById("content").appendChild(txtP2);
        var txtP3 = document.createElement('input');
        txtP3.disabled = 'true';
        txtP3.value = "Datum";
        txtP3.style.height = "40px";
        txtP3.style.width = "200px";
        txtP3.style.textAlign = 'center';
        document.getElementById("content").appendChild(txtP3);
        document.getElementById("content").appendChild(document.createElement("br"));

        $.get({
            url: 'api/pacijenti/myZdravstveniKarton/' + korisnik.id,
            contentType: 'application/json',
            headers: {
                'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
            },
            success: function (zk) {

                for (let p of zk.pregledi)
                {
                    var txtPregled1 = document.createElement('input');
                    txtPregled1.disabled = 'true';
                    txtPregled1.value = p.tipPregleda.naziv;
                    txtPregled1.classList.add("input--style-4");
                    txtPregled1.style.height = "40px";
                    txtPregled1.style.width = "200px";
                    document.getElementById("content").appendChild(txtPregled1);
                    var txtPregled2 = document.createElement('input');
                    txtPregled2.disabled = 'true';
                    txtPregled2.value = p.lekar.ime + " " + p.lekar.prezime;
                    txtPregled2.classList.add("input--style-4");
                    txtPregled2.style.height = "40px";
                    txtPregled2.style.width = "200px";
                    document.getElementById("content").appendChild(txtPregled2);
                    var txtPregled3 = document.createElement('input');
                    txtPregled3.disabled = 'true';
                    txtPregled3.value = p.termin.pocetak.substr(0, 10);
                    txtPregled3.classList.add("input--style-4");
                    txtPregled3.style.height = "40px";
                    txtPregled3.style.width = "200px";
                    document.getElementById("content").appendChild(txtPregled3);
                    document.getElementById("content").appendChild(document.createElement("br"));
                }

                var lblOperacije = document.createElement("H1");
                lblOperacije.appendChild(document.createTextNode("Operacije"));
                document.getElementById("content").appendChild(lblOperacije);

                var txtO1 = document.createElement('input');
                txtO1.disabled = 'true';
                txtO1.value = "Lekar";
                txtO1.style.height = "40px";
                txtO1.style.width = "200px";
                txtO1.style.textAlign = 'center';
                document.getElementById("content").appendChild(txtO1);
                var txtO1 = document.createElement('input');
                txtO1.disabled = 'true';
                txtO1.value = "Datum";
                txtO1.style.height = "40px";
                txtO1.style.width = "200px";
                txtO1.style.textAlign = 'center';
                document.getElementById("content").appendChild(txtO1);
                document.getElementById("content").appendChild(document.createElement("br"));

                $.get({
                    url: 'api/pacijenti/mojeOperacije/' + korisnik.id,
                    contentType: 'application/json',
                    headers: {
                        'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
                    },
                    success: function (operacije) {

                        for (let o of operacije)
                        {
                            var txtOperacija1 = document.createElement('input');
                            txtOperacija1.disabled = 'true';
                            txtOperacija1.value = o.lekar.ime + " " + o.lekar.prezime;
                            txtOperacija1.classList.add("input--style-4");
                            txtOperacija1.style.height = "40px";
                            txtOperacija1.style.width = "200px";
                            document.getElementById("content").appendChild(txtOperacija1);
                            var txtOperacija2 = document.createElement('input');
                            txtOperacija2.disabled = 'true';
                            txtOperacija2.value = o.termin.pocetak.substr(0, 10);
                            txtOperacija2.classList.add("input--style-4");
                            txtOperacija2.style.height = "40px";
                            txtOperacija2.style.width = "200px";
                            document.getElementById("content").appendChild(txtOperacija2);
                            document.getElementById("content").appendChild(document.createElement("br"));
                        }
                    }
                });
            }
        });
    });
    $("#content").fadeIn(500);
}

function generisiOcjenjivanjeLekara(){
    return function(){
        $("#content").fadeOut(100, function(){
            document.getElementById("content").innerHTML = "";
            var lblLekari = document.createElement("H2");
            var t = document.createTextNode("Lekari");
            lblLekari.appendChild(t);
            document.getElementById("content").appendChild(lblLekari);

            var cbLekar = document.createElement('select');
            cbLekar.id = 'lekar';

            $.get({
                url:'api/pacijenti/getLekari/' + korisnik.id,
                contentType: 'application/json',
                headers: {
                    'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
                },
                success: function(lekari)
                {
                    for(let lekar of lekari)
                    {
                        var option = document.createElement('option');
                        option.value=lekar.id;
                        option.innerHTML=lekar.ime + ' ' + lekar.prezime;
                        cbLekar.appendChild(option);
                    }
                }
            });
            cbLekar.classList.add("input--style-4");
            cbLekar.style.height = "40px";
            cbLekar.style.width = "250px";
            document.getElementById("content").appendChild(cbLekar);
            document.getElementById("content").appendChild(document.createElement("br"));
            document.getElementById("content").appendChild(document.createTextNode("Vaša ocena:"));
            document.getElementById("content").appendChild(document.createElement("br"));

            var cbOcjena = document.createElement('select');
            cbOcjena.id = 'ocenaLekar';

            for(let broj of [1, 2, 3, 4, 5])
            {
                var option = document.createElement('option');
                option.value=broj;
                option.innerHTML=broj;
                cbOcjena.appendChild(option);
            }

            cbOcjena.classList.add("input--style-4");
            cbOcjena.style.height = "40px";
            cbOcjena.style.width = "50px";
            document.getElementById("content").appendChild(cbOcjena);

            document.getElementById("content").appendChild(document.createElement("br"));

            var submit = document.createElement("BUTTON");
            submit.classList.add("btn", "btn--radius-2", "btn--light-blue");
            submit.innerHTML = "Oceni lekara";
            submit.id = "oceniLekara";
            submit.onclick = oceniLekara();
            document.getElementById("content").appendChild(submit);
        });
        $("#content").fadeIn(500);
    }
}

function generisiOcjenjivanjeKlinike(){
    return function(){
        $("#content").fadeOut(100, function(){
            document.getElementById("content").innerHTML = "";

            var lblKlinike = document.createElement("H2");
            lblKlinike.appendChild(document.createTextNode("Klinike"));
            document.getElementById("content").appendChild(lblKlinike);

            var cbKlinike = document.createElement('select');
            cbKlinike.id = 'klinika';

            $.get({
                url:'api/pacijenti/getKlinike/' + korisnik.id,
                contentType: 'application/json',
                headers: {
                    'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
                },
                success: function(klinike)
                {
                    for(let klinika of klinike)
                    {
                        var option = document.createElement('option');
                        option.value=klinika.id;
                        option.innerHTML=klinika.naziv;
                        cbKlinike.appendChild(option);
                    }
                }
            });
            cbKlinike.classList.add("input--style-4");
            cbKlinike.style.height = "40px";
            cbKlinike.style.width = "250px";
            document.getElementById("content").appendChild(cbKlinike);
            document.getElementById("content").appendChild(document.createElement("br"));
            document.getElementById("content").appendChild(document.createTextNode("Vaša ocena:"));
            document.getElementById("content").appendChild(document.createElement("br"));

            var cbOcjenaK = document.createElement('select');
            cbOcjenaK.id = 'ocenaKlinika';

            for(let broj of [1, 2, 3, 4, 5])
            {
                var option = document.createElement('option');
                option.value=broj;
                option.innerHTML=broj;
                cbOcjenaK.appendChild(option);
            }

            cbOcjenaK.classList.add("input--style-4");
            cbOcjenaK.style.height = "40px";
            cbOcjenaK.style.width = "50px";
            document.getElementById("content").appendChild(cbOcjenaK);

            document.getElementById("content").appendChild(document.createElement("br"));

            var submitK = document.createElement("BUTTON");
            submitK.classList.add("btn", "btn--radius-2", "btn--light-blue");
            submitK.innerHTML = "Oceni kliniku";
            submitK.id = "oceniLekara";
            submitK.onclick = oceniKliniku();
            document.getElementById("content").appendChild(submitK);

        });
        $("#content").fadeIn(500);
    }
}

function oceniLekara(){
    return function(){
        $.get({
            url:'api/pacijenti/oceniLekara/' + document.getElementById("lekar").value + '/' + document.getElementById("ocenaLekar").value,
            contentType: 'application/json',
            headers: {
                'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
            },
            success: function()
            {
                generisiIstoriju();
            }
        });
    }
}

function oceniKliniku(){
    return function(){
        $.get({
            url:'api/pacijenti/oceniKliniku/' + document.getElementById("klinika").value + '/' + document.getElementById("ocenaKlinika").value,
            contentType: 'application/json',
            headers: {
                'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
            },
            success: function()
            {
                generisiIstoriju();
            }
        });
    }
}

function generisiZdravstveniKarton(korisnik) {
    $("#content").fadeOut(100, function(){
        document.getElementById("content").innerHTML = "";
        $.get({
            url: 'api/pacijenti/myZdravstveniKarton/' + korisnik.id,
            contentType: 'application/json',
            headers: {
                'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
            },
            success: function (zk) {
                var prviRed = document.createElement("var");
                prviRed.classList.add("row", "wrapper--w680");
                var varVisina = document.createElement("var");
                varVisina.classList.add("col-2", "input-group");
                varVisina.appendChild(document.createTextNode("Visina"));
                varVisina.appendChild(document.createElement("br"));
                var txtVisina = document.createElement('input');
                txtVisina.disabled = 'true';
                txtVisina.value = zk.visina + " cm";
                txtVisina.classList.add("input--style-4");
                txtVisina.style.height = "40px";
                txtVisina.style.width = "250px";
                varVisina.appendChild(txtVisina);
                prviRed.appendChild(varVisina);

                var varMasa = document.createElement("var");
                varMasa.classList.add("col-2", "input-group");
                varMasa.appendChild(document.createTextNode("Masa"));
                varMasa.appendChild(document.createElement("br"));
                var txtMasa = document.createElement('input');
                txtMasa.disabled = 'true';
                txtMasa.value = zk.masa + " kg";
                txtMasa.classList.add("input--style-4");
                txtMasa.style.height = "40px";
                txtMasa.style.width = "250px";
                varMasa.appendChild(txtMasa);
                prviRed.appendChild(varMasa);

                document.getElementById("content").appendChild(prviRed);

                var drugiRed = document.createElement("var");
                drugiRed.classList.add("row", "wrapper--w680");
                var varKrvnaGrupa = document.createElement("var");
                varKrvnaGrupa.classList.add("col-2", "input-group");
                varKrvnaGrupa.appendChild(document.createTextNode("Krvna grupa"));
                varKrvnaGrupa.appendChild(document.createElement("br"));
                var txtKrvnaGrupa = document.createElement('input');
                txtKrvnaGrupa.disabled = 'true';
                txtKrvnaGrupa.value = zk.krvnaGrupa;
                txtKrvnaGrupa.classList.add("input--style-4");
                txtKrvnaGrupa.style.height = "40px";
                txtKrvnaGrupa.style.width = "250px";
                varKrvnaGrupa.appendChild(txtKrvnaGrupa);
                drugiRed.appendChild(varKrvnaGrupa);

                var varDioptrija = document.createElement("var");
                varDioptrija.classList.add("col-2", "input-group");
                varDioptrija.appendChild(document.createTextNode("Dioptrija"));
                varDioptrija.appendChild(document.createElement("br"));
                var txtDioptrija = document.createElement('input');
                txtDioptrija.disabled = 'true';
                txtDioptrija.value = zk.dioptrija;
                txtDioptrija.classList.add("input--style-4");
                txtDioptrija.style.height = "40px";
                txtDioptrija.style.width = "250px";
                varDioptrija.appendChild(txtDioptrija);
                drugiRed.appendChild(varDioptrija);

                document.getElementById("content").appendChild(drugiRed);

                var varAlergije = document.createElement("var");
                varAlergije.classList.add("col-2", "input-group");
                varAlergije.appendChild(document.createTextNode("Alergije"));
                varAlergije.appendChild(document.createElement("br"));
                var txtAlergije = document.createElement('input');
                txtAlergije.disabled = 'true';
                txtAlergije.value = zk.alergije;
                txtAlergije.classList.add("input--style-4");
                txtAlergije.style.height = "40px";
                txtAlergije.style.width = "600px";
                varAlergije.appendChild(txtAlergije);
                document.getElementById("content").appendChild(varAlergije);

                var lblDijagnoze = document.createElement("H1");
                lblDijagnoze.appendChild(document.createTextNode("Dijagnoze"));
                document.getElementById("content").appendChild(lblDijagnoze);

                for (let d of zk.dijagnoze)
                {
                    var txtDijagnoza = document.createElement('input');
                    txtDijagnoza.disabled = 'true';
                    txtDijagnoza.value = d.nazivDijagnoze;
                    txtDijagnoza.classList.add("input--style-4");
                    txtDijagnoza.style.height = "40px";
                    txtDijagnoza.style.width = "250px";
                    document.getElementById("content").appendChild(txtDijagnoza);
                    document.getElementById("content").appendChild(document.createElement("br"));
                }
            }
        });
    });
    $("#content").fadeIn(500);
}

function generisiKlinike() {
    var today = new Date();
    var dd = String(today.getDate()).padStart(2, '0');
    var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    var yyyy = today.getFullYear();

    today = yyyy + '-' + mm + '-' + dd;
   prikazKlinika([], '', today, '', 1);
}

function prikazKlinika(klinike, tipP, datumP, lokacijaP, ocjenaP ){
    $("#content").fadeOut(100, function(){
        document.getElementById("content").innerHTML = "";
        var prviRed = document.createElement("var");
        prviRed.classList.add("row", "wrapper--w680");
        var varTip = document.createElement("var");
        varTip.classList.add("col-2", "input-group");
        varTip.appendChild(document.createTextNode("Tip pregleda*"));
        varTip.appendChild(document.createElement("br"));
        var txtTip = document.createElement('select');
        txtTip.id = 'tipPregleda';

        $.get({
            url:'api/tipovipregleda/getDistinct',
            contentType: 'application/json',
            headers: {
                'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
            },
            success: function(tipovi)
            {
                for(let tip of tipovi)
                {
                    var option = document.createElement('option');
                    option.value=tip;
                    option.innerHTML=tip;
                    txtTip.appendChild(option);
                }
            }
        });
        txtTip.value = tipP;
        txtTip.classList.add("input--style-4");
        txtTip.style.height = "40px";
        txtTip.style.width = "250px";
        varTip.appendChild(txtTip);
        prviRed.appendChild(varTip);

        var varDan = document.createElement("var");
        varDan.classList.add("col-2", "input-group");
        varDan.appendChild(document.createTextNode("Dan*"));
        varDan.appendChild(document.createElement("br"));
        var txtDan = document.createElement('input');
        txtDan.setAttribute("type", "date");
        txtDan.id = 'datum';

        var today = new Date();
        var dd = String(today.getDate()).padStart(2, '0');
        var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
        var yyyy = today.getFullYear();

        today = yyyy + '-' + mm + '-' + dd;
        txtDan.min = today;
        txtDan.value = datumP;

        txtDan.style.height = "40px";
        txtDan.style.width = "250px";
        txtDan.style.textAlign = 'center';
        varDan.appendChild(txtDan);
        prviRed.appendChild(varDan);

        var varLokacija = document.createElement("var");
        varLokacija.classList.add("col-2", "input-group");
        varLokacija.appendChild(document.createTextNode("Lokacija"));
        varLokacija.appendChild(document.createElement("br"));
        var txtLokacija = document.createElement('input');
        txtLokacija.id = 'lokacija';
        if( lokacijaP !== "NULL") txtLokacija.value = lokacijaP;
        txtLokacija.setAttribute("type", "text");
        txtLokacija.style.height = "40px";
        txtLokacija.style.width = "250px";
        txtLokacija.classList.add("input--style-4");
        varLokacija.appendChild(txtLokacija);
        prviRed.appendChild(varLokacija);

        var varOcjena = document.createElement("var");
        varOcjena.classList.add("col-2", "input-group");
        varOcjena.appendChild(document.createTextNode("Minimalna ocena"));
        varOcjena.appendChild(document.createElement("br"));
        var txtOcjena = document.createElement('select');
        txtOcjena.id = 'ocjena';

        var ocjene = [ 1, 2, 3, 4, 5];

        for(let o of ocjene)
        {
            var op = document.createElement('option');
            op.value=o;
            op.innerHTML=o;
            txtOcjena.appendChild(op);
        }

        txtOcjena.value = ocjenaP;
        txtOcjena.style.height = "40px";
        txtOcjena.style.width = "250px";
        txtOcjena.classList.add("input--style-4");
        varOcjena.appendChild(txtOcjena);
        prviRed.appendChild(varOcjena);

        document.getElementById("content").appendChild(prviRed);

        var submit = document.createElement("BUTTON");
        submit.classList.add("btn", "btn--radius-2", "btn--light-blue");
        submit.innerHTML = "Pretraži";
        submit.onclick = filtrirajKlinike();
        document.getElementById("content").appendChild(submit);
        document.getElementById("content").appendChild(document.createElement("br"));

        var txtP1 = document.createElement('input');
        txtP1.disabled = 'true';
        txtP1.value = "Naziv";
        txtP1.style.height = "40px";
        txtP1.style.width = "180px";
        txtP1.style.textAlign = 'center';
        document.getElementById("content").appendChild(txtP1);
        var txtP2 = document.createElement('input');
        txtP2.disabled = 'true';
        txtP2.value = "Lokacija";
        txtP2.style.height = "40px";
        txtP2.style.width = "280px";
        txtP2.style.textAlign = 'center';
        document.getElementById("content").appendChild(txtP2);
        var txtP3 = document.createElement('input');
        txtP3.disabled = 'true';
        txtP3.value = "Ocena";
        txtP3.style.height = "40px";
        txtP3.style.width = "80px";
        txtP3.style.textAlign = 'center';
        document.getElementById("content").appendChild(txtP3);
        var txtP4 = document.createElement('input');
        txtP4.disabled = 'true';
        txtP4.value = "Profil";
        txtP4.style.height = "40px";
        txtP4.style.width = "80px";
        txtP4.style.textAlign = 'center';
        document.getElementById("content").appendChild(txtP4);
        document.getElementById("content").appendChild(document.createElement("br"));

        for(let klinika of klinike)
        {
            var txt1 = document.createElement('input');
            txt1.disabled = 'true';
            txt1.value = klinika.naziv;
            txt1.style.height = "40px";
            txt1.style.backgroundColor = "white";
            txt1.style.width = "180px";
            txt1.style.textAlign = 'center';
            document.getElementById("content").appendChild(txt1);
            var txt2 = document.createElement('input');
            txt2.disabled = 'true';
            txt2.value = klinika.lokacija;
            txt2.style.height = "40px";
            txt2.style.backgroundColor = "white";
            txt2.style.width = "280px";
            txt2.style.textAlign = 'center';
            document.getElementById("content").appendChild(txt2);
            var txt3 = document.createElement('input');
            txt3.disabled = 'true';
            txt3.value = Math.round(klinika.prosecnaOcena * 100) / 100;
            txt3.style.height = "40px";
            txt3.style.backgroundColor = "white";
            txt3.style.width = "80px";
            txt3.style.textAlign = 'center';
            document.getElementById("content").appendChild(txt3);
            var btn = document.createElement("BUTTON");
            btn.innerHTML = "➤";
            btn.style.fontSize = "30px";
            btn.id = klinika.naziv;
            btn.onclick = infoKlinike(klinika);
            btn.style.height = "40px";
            btn.style.width = "80px";
            btn.style.textAlign = 'center';
            document.getElementById("content").appendChild(btn);
            document.getElementById("content").appendChild(document.createElement("br"));
        }
    });

    $("#content").fadeIn(500);
}

function infoKlinike(klinika)
{
    return function(){
        $("#content").fadeOut(100, function(){
            var tipPregleda = $('#tipPregleda').val();
            var datum = $('#datum').val();
            document.getElementById("content").innerHTML = "";
            var naziv = document.createElement("h2");
            naziv.appendChild(document.createTextNode(klinika.naziv));
            document.getElementById("content").appendChild(naziv);

            var tip = document.createElement("var");
            tip.classList.add("col-2", "input-group");
            tip.appendChild(document.createTextNode("Tip pregleda"));
            tip.appendChild(document.createElement("br"));
            var txtTip = document.createElement('input');
            txtTip.value = tipPregleda;
            txtTip.disabled = 'true';
            txtTip.setAttribute("type", "text");
            txtTip.style.height = "40px";
            txtTip.style.width = "250px";
            txtTip.classList.add("input--style-4");
            tip.appendChild(txtTip);

            var cena = document.createElement("var");
            cena.classList.add("col-2", "input-group");
            cena.appendChild(document.createTextNode("Cena"));
            cena.appendChild(document.createElement("br"));
            var txtCena = document.createElement('input');
            $.get({
                url: 'api/klinike/getCena/' + klinika.id + '/' + tipPregleda,
                contentType: 'application/json',
                headers: {
                    'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
                },
                success: function (cena) {
                    if (cena<0){
                        txtCena.value = 'Nedostupno';
                    }
                    else{
                        txtCena.value = cena;
                    }
                }
            });
            txtCena.disabled = 'true';
            txtCena.setAttribute("type", "text");
            txtCena.style.height = "40px";
            txtCena.style.width = "250px";
            txtCena.classList.add("input--style-4");
            cena.appendChild(txtCena);

            var prviRed = document.createElement("var");
            prviRed.classList.add("row", "wrapper--w680");
            prviRed.appendChild(tip);
            prviRed.appendChild(cena);
            document.getElementById("content").appendChild(prviRed);

            var dat = document.createElement("var");
            dat.classList.add("col-2", "input-group");
            dat.appendChild(document.createTextNode("Datum"));
            dat.appendChild(document.createElement("br"));
            var txtDat = document.createElement('input');
            txtDat.value = datum;
            txtDat.disabled = 'true';
            txtDat.setAttribute("type", "text");
            txtDat.style.height = "40px";
            txtDat.style.width = "250px";
            txtDat.classList.add("input--style-4");
            dat.appendChild(txtDat);

            document.getElementById("content").appendChild(dat);

            document.getElementById("content").appendChild(document.createElement("br"));

            var lekari = document.createElement("h3");
            lekari.appendChild(document.createTextNode("Dostupni lekari"));
            document.getElementById("content").appendChild(lekari);

            document.getElementById("content").appendChild(document.createElement("br"));

            $.get({
                url: 'api/klinike/getSlobodniPregledi/' + klinika.id + '/' + datum + '/' + tipPregleda,
                contentType: 'application/json',
                headers: {
                    'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
                },
                success: function (pregledi) {
                    var txtP1 = document.createElement('input');
                    txtP1.disabled = 'true';
                    txtP1.value = "Ime i prezime";
                    txtP1.style.height = "40px";
                    txtP1.style.width = "180px";
                    txtP1.style.textAlign = 'center';
                    document.getElementById("content").appendChild(txtP1);
                    var txtP2 = document.createElement('input');
                    txtP2.disabled = 'true';
                    txtP2.value = "Ocena";
                    txtP2.style.height = "40px";
                    txtP2.style.width = "80px";
                    txtP2.style.textAlign = 'center';
                    document.getElementById("content").appendChild(txtP2);
                    var txtP3 = document.createElement('input');
                    txtP3.disabled = 'true';
                    txtP3.value = "Vreme početka";
                    txtP3.style.height = "40px";
                    txtP3.style.width = "120px";
                    txtP3.style.textAlign = 'center';
                    document.getElementById("content").appendChild(txtP3);
                    var txtP4 = document.createElement('input');
                    txtP4.disabled = 'true';
                    txtP4.value = "Vreme kraja";
                    txtP4.style.height = "40px";
                    txtP4.style.width = "100px";
                    txtP4.style.textAlign = 'center';
                    document.getElementById("content").appendChild(txtP4);
                    var txtP5 = document.createElement('input');
                    txtP5.disabled = 'true';
                    txtP5.value = "Zakazivanje";
                    txtP5.style.height = "40px";
                    txtP5.style.width = "100px";
                    txtP5.style.textAlign = 'center';
                    document.getElementById("content").appendChild(txtP5);
                    document.getElementById("content").appendChild(document.createElement("br"));

                    for(let pregled of pregledi)
                    {
                        var txt1 = document.createElement('input');
                        txt1.disabled = 'true';
                        txt1.value = pregled.lekar.ime + " " + pregled.lekar.prezime;
                        txt1.style.height = "40px";
                        txt1.style.backgroundColor = "white";
                        txt1.style.width = "180px";
                        txt1.style.textAlign = 'center';
                        document.getElementById("content").appendChild(txt1);
                        var txt2 = document.createElement('input');
                        txt2.disabled = 'true';
                        txt2.value = Math.round(pregled.lekar.prosecnaOcena * 100) / 100;
                        txt2.style.height = "40px";
                        txt2.style.backgroundColor = "white";
                        txt2.style.width = "80px";
                        txt2.style.textAlign = 'center';
                        document.getElementById("content").appendChild(txt2);
                        var txt3 = document.createElement('input');
                        txt3.disabled = 'true';
                        txt3.value = pregled.termin.pocetak.substring(11, 16);
                        txt3.style.height = "40px";
                        txt3.style.backgroundColor = "white";
                        txt3.style.width = "120px";
                        txt3.style.textAlign = 'center';
                        document.getElementById("content").appendChild(txt3);
                        var txt4 = document.createElement('input');
                        txt4.disabled = 'true';
                        txt4.value = pregled.termin.kraj.substring(11, 16);
                        txt4.style.height = "40px";
                        txt4.style.backgroundColor = "white";
                        txt4.style.width = "100px";
                        txt4.style.textAlign = 'center';
                        document.getElementById("content").appendChild(txt4);
                        var btn = document.createElement("BUTTON");
                        btn.innerHTML = "➤";
                        btn.style.fontSize = "30px";
                        btn.onclick = zakazivanje(pregled);
                        btn.style.height = "40px";
                        btn.style.width = "100px";
                        btn.style.textAlign = 'center';
                        document.getElementById("content").appendChild(btn);
                        document.getElementById("content").appendChild(document.createElement("br"));
                    }
                }
            });

        });

        $("#content").fadeIn(500);
    }
}

function zakazivanje(pregled){
    return function(){
        let token = JSON.parse(localStorage.getItem('jwt'))
        $.ajax
        ({
            type: "GET",
            url: 'api/korisnici/whoami',
            contentType: 'application/json',
            headers: {
                'Authorization': 'Bearer ' + token
            },
            success: function (ulogovan){
                $.post({
                    url: 'api/lekari/zakaziPregled/' + ulogovan.id,
                    data: JSON.stringify(pregled),
                    contentType: 'application/json',
                    headers: {
                        'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
                    },
                    success: function() {
                        alert("Pregled zakazan.")
                        generisiKlinike();
                    },
                    error: function() {
                        alert("Greška.")
                    }
                });
            }
        })
    }
}

function filtrirajKlinike() {
    return function(){
        tip = $('#tipPregleda').val();
        datum = $('#datum').val();

        if (datum === ""){
            alert("Unesite datum pregleda!");
            return;
        }

        lokacija = $('#lokacija').val();
        ocjena = $('#ocjena').val();

        if (lokacija === ""){
            lokacija = "NULL";
        }

        $.get({
            url: 'api/klinike/getKlinike/' + tip + '/' + datum + '/' + lokacija + '/' + ocjena,
            contentType: 'application/json',
            headers: {
                'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
            },
            success: function (klinike) {
                prikazKlinika(klinike, tip, datum, lokacija, ocjena);
            }
        });
    }
}