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

    document.getElementById("content").innerHTML = "";
    var textnode = document.createTextNode("Uskoro ce biti moguce overiti recept");
    document.getElementById("content").appendChild(textnode);

    $("#content").fadeIn(500);

}

