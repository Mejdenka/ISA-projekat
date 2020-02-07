// Dugmad na pocetnoj stranici medicinske sestre
function pocetnaMedicinskaSestra(ulogovan) {
    korisnik = ulogovan;
    var imeKorisnika = korisnik.ime + " " + korisnik.prezime;
    var nazivi = ["Lista pacijenata", "Radni kalendar", "Zakazi godisnji odmor/odsustvo", imeKorisnika, "Overi recept"];

    for(let naziv of nazivi) {
        //pravljenje dugmadi
        var btn = document.createElement("BUTTON");
        btn.classList.add("btn", "btn--radius-2", "btn--light-blue");
        btn.innerHTML = naziv;
        document.getElementById("navbar").appendChild(btn);
        //dodjela specificnih id-jeva dugmadima
        switch (naziv) {
            case "Lista pacijenata":
                btn.id = "pacijentiBtn";
                break;
            case "Radni kalendar":
                btn.id = "radniKalendarBtn";
                break;
            case "Zakazi godisnji odmor/odsustvo":
                btn.id = "odmorOdsustvoBtn";
                break;
            case imeKorisnika:
                btn.id = "profilBtn";
                break;
            case "Overi recept":
                btn.id = "overiBtn";
                break;
        }
    }

    // Pozivi funkcije za klikove na dugmad
    $('body').on('click', '#pacijentiBtn', function(e) {
        prikaziPacijenteKlinike();
    });

    $('body').on('click', '#radniKalendarBtn', function(e) {
        radniKalendarMedSestre();
    });

    $('body').on('click', '#odmorOdsustvoBtn', function(e) {
        zakaziOdmorOdsustvo();
    });

    $('body').on('click', '#overiBtn', function(e) {
        overiRecept();
    });

}


function prikaziPacijenteKlinike(){
    $("#content").fadeOut(100, function() {


            $.ajax
            ({
                type: "GET",
                url: 'api/sestra/getPacijenti',
                contentType: 'application/json',
                headers: {
                    'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
                },
                success: function (pacijenti) {

                    $("#content").fadeOut(100, function () {
                        var content = document.getElementById('content')
                        content.innerHTML = "";

                        var searchDiv = document.createElement("DIV");
                        searchDiv.align = "center";
                        var search = document.createElement("INPUT");
                        search.type = "text";
                        search.classList.add("input--style-4");
                        search.id = "search";
                        search.name = "search";

                        searchDiv.appendChild(search);
                        content.append(searchDiv);
                        $('#search').keyup(function(){
                            search_table($(this).val());
                        });
                        var naslov = document.createElement("header");
                        naslov.innerText = "Pacijenti";
                        naslov.fontSize = "35px"
                        content.appendChild(naslov);

                        var table = document.createElement('table');
                        table.classList.add("tabela");
                        table.id = "tabelaPacijenata";
                        var tableRef = document.createElement('tbody');

                        for (let pacijent of pacijenti) {
                            var podaciPacijenta = tableRef.insertRow();
                            var imePacijenta = podaciPacijenta.insertCell(0);
                            var imePacijentaText = document.createTextNode(pacijent.ime);
                            imePacijenta.appendChild(imePacijentaText);

                            var prezimePacijenta = podaciPacijenta.insertCell(1);
                            var prezimePacijentaText = document.createTextNode(pacijent.prezime);
                            prezimePacijenta.appendChild(prezimePacijentaText);

                            /*var datRodjenja = podaciPacijenta.insertCell(2);
                            var datum = document.createTextNode(pacijent.datumRodjenja);
                            datRodjenja.appendChild(datum);*/

                            var jbo = podaciPacijenta.insertCell(2);
                            var jboText = document.createTextNode(pacijent.jbo);
                            jbo.appendChild(jboText);

                            var email = podaciPacijenta.insertCell(3);
                            var emailPacijentaText = document.createTextNode(pacijent.email);
                            email.appendChild(emailPacijentaText);

                            var profilPacijenta = podaciPacijenta.insertCell(4);
                            var profilPacijentaBtn = document.createElement("BUTTON");
                            profilPacijentaBtn.classList.add("btn--radius", "btn--light-blue", "btn--tabela");
                            profilPacijentaBtn.innerText = "Profil";
                            profilPacijentaBtn.style.color = "white";
                            profilPacijentaBtn.style.fontSize = "15px"
                            profilPacijentaBtn.style.height = "30px"
                            profilPacijentaBtn.onclick = prikaziProfilPacijenta(pacijent.id);
                            profilPacijenta.appendChild(profilPacijentaBtn);

                        }
                        table.appendChild(tableRef);
                        content.appendChild(table);
                    });
                    $("#content").fadeIn(500);
                }
            });

        });
}
// ********** RADNI KALENDAR **********
function radniKalendarMedSestre(){
    document.getElementById("content").innerHTML = "";


    $("#content").fadeIn(500);

}


function zakaziOdmorOdsustvo(){
    document.getElementById("content").innerHTML = "";
    var textnode = document.createTextNode("Uskoro ce biti moguce zakazati odmor ili odsustvo");
    document.getElementById("content").appendChild(textnode);

    $("#content").fadeIn(500);
}

function overiRecept(){
    var ulogovan = JSON.parse(localStorage.getItem('ulogovan'));
    $("#content").fadeOut(100, function() {

        $.get({
            url: 'api/sestra/getRecepti',
            contentType: 'application/json',
            headers: {
                'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
            },
            success: function(recepti){
                $("#content").fadeOut(100, function () {
                    var content = document.getElementById('content')
                    content.innerHTML = "";

                    var naslov = document.createElement("header");
                    naslov.innerText = "- - - Recepti - - -";
                    naslov.fontSize = "50px"
                    content.appendChild(naslov);

                    var table = document.createElement('table');
                    table.classList.add("tabela");
                    table.id = "tabelaRecepata";
                    var tableRef = document.createElement('tbody');

                    for(let recept of recepti){
                        var podaciRecepta = tableRef.insertRow();
                        var receptLek = podaciRecepta.insertCell(0);
                        var receptLekText = document.createTextNode(recept.lek);
                        receptLek.appendChild(receptLekText);

                        var receptDijagnoza = podaciRecepta.insertCell(1);
                        var receptDijagnozaText = document.createTextNode(recept.dijagnoza);
                        receptDijagnoza.appendChild(receptDijagnozaText);

                        var receptIzvestaj = podaciRecepta.insertCell(2);
                        var receptIzvestajText = document.createTextNode(recept.izvestaj);
                        receptIzvestaj.appendChild(receptIzvestajText);

                        var receptIzvestaj = podaciRecepta.insertCell(3);
                        var receptIzvestajBtn = document.createElement("BUTTON");
                        receptIzvestajBtn.classList.add("btn--radius", "btn--light-blue", "btn--tabela");
                        receptIzvestajBtn.innerText = "Recept i izvestaj";
                        receptIzvestajBtn.style.color = "white";
                        receptIzvestajBtn.style.fontSize = "15px"
                        receptIzvestajBtn.style.height = "30px"
                        receptIzvestajBtn.onclick = overiIzmeni(recept)
                        receptIzvestaj.appendChild(receptIzvestajBtn);

                    }

                    table.appendChild(tableRef);
                    content.appendChild(table);

                });
                $("#content").fadeIn(500);
            }



        });

    });

}

function overiIzmeni(recept){
    return function(){
        var ulogovan = JSON.parse(localStorage.getItem('ulogovan'));

        var modal = document.getElementById("overiIzmeniModal");
        modal.style.display = "block";

        var span = document.getElementById("closeOveriIzmeni");

        span.onclick = function () {
            modal.style.display = "none";
        }

        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }

        var content = document.getElementById("overiIzmeniDiv");
        content.innerHTML = "";

        var naslov = document.createElement("Header");
        naslov.innerText = "Overa recepta i izmena izvestaja pregleda ";
        naslov.style.fontSize = "20px";
        naslov.style.marginTop = "10px";
        naslov.style.marginBottom = "10px";
        content.appendChild(naslov);
        content.appendChild(document.createElement("br"));

        var red = document.createElement("var");
        red.classList.add("row", "wrapper--w680");
        var varDijagnoza = document.createElement("var");
        varDijagnoza.classList.add("col-2", "input-group");
        var dijagnoza = document.createTextNode("Dijagnoza");
        varDijagnoza.appendChild(dijagnoza);
        varDijagnoza.appendChild(document.createElement("br"));
        var txtDijagnoza = document.createElement('input');
        txtDijagnoza.type = 'text';
        txtDijagnoza.disabled = true;
        txtDijagnoza.value = recept.dijagnoza;
        txtDijagnoza.id = "dijagnoza";
        txtDijagnoza.classList.add("input--style-4");
        txtDijagnoza.style.height = "40px";
        txtDijagnoza.style.width = "250px";
        varDijagnoza.appendChild(txtDijagnoza);
        red.appendChild(varDijagnoza);

        var varLek = document.createElement("var");
        varLek.classList.add("col-2", "input-group");
        var lek = document.createTextNode("Terapija");
        varLek.appendChild(lek);
        varLek.appendChild(document.createElement("br"));
        var txtLek = document.createElement('input');
        txtLek.disabled = true;
        txtLek.value = recept.lek;
        txtLek.id = "lek";
        txtLek.classList.add("input--style-4");
        txtLek.style.height = "40px";
        txtLek.style.width = "250px";
        varLek.appendChild(txtLek);
        red.appendChild(varLek);

        content.appendChild(red);

        var red1 = document.createElement("var");
        red1.classList.add("row", "wrapper--w680");
        var varIzvestaj = document.createElement("var");
        varIzvestaj.classList.add("col-2", "input-group");
        var izvestaj = document.createTextNode("Izvestaj o pregledu");
        varIzvestaj.appendChild(izvestaj);
        varIzvestaj.appendChild(document.createElement("br"));
        var txtIzvestaj = document.createElement('input');
        txtIzvestaj.type = 'text';
        txtIzvestaj.value = recept.izvestaj;
        txtIzvestaj.id = "izvestaj";
        txtIzvestaj.classList.add("input--style-4");
        txtIzvestaj.style.height = "120px";
        txtIzvestaj.style.width = "580px";
        varIzvestaj.appendChild(txtIzvestaj);
        red1.appendChild(varIzvestaj);
        red1.appendChild(varIzvestaj);
        content.appendChild(red1);

        var overiIzmeniBtn = document.createElement("BUTTON");
        overiIzmeniBtn.classList.add("btn--radius", "btn--light-blue", "btn--tabela");
        overiIzmeniBtn.innerText = "Overi recept i sacuvaj izmene";
        overiIzmeniBtn.style.color = "white";
        overiIzmeniBtn.style.fontSize = "15px";
        overiIzmeniBtn.style.height = "30px";
        overiIzmeniBtn.onclick = function(){
            var izvestaj = $('#izvestaj').val();
            var id = recept.id;

            $.post({
                url: 'api/sestra/overiRecept',
                data: JSON.stringify({id, izvestaj}),
                contentType: 'application/json',
                headers: {
                    'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('jwt'))
                },
                success: function(){
                    alert("Recept je overen i sacuvane su izmene.");
                    return;
                },
                error: function(){
                    alert("Greska prilikom overe recepta! Pokusajte ponovo.");
                    return;
                }
            });

        }
        content.appendChild(overiIzmeniBtn);


    }
}

