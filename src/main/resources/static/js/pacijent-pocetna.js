

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
                var prviRed = document.createElement("var");
                prviRed.classList.add("row", "wrapper--w680");
                var varTip = document.createElement("var");
                varTip.classList.add("col-2", "input-group");
                var tip = document.createTextNode("Tip pregleda");
                varTip.appendChild(tip);
                varTip.appendChild(document.createElement("br"));
                var txtTip = document.createElement('select');
                var option1 = document.createElement('option');
                option1.value="glava";
                option1.innerHTML="Pregled glave";
                txtTip.appendChild(option1);
                txtTip.classList.add("input--style-4");
                txtTip.style.height = "40px";
                txtTip.style.width = "250px";
                varTip.appendChild(txtTip);
                prviRed.appendChild(varTip);

                var varLokacija = document.createElement("var");
                varLokacija.classList.add("col-2", "input-group");
                var lokacija = document.createTextNode("Lokacija");
                varLokacija.appendChild(lokacija);
                varLokacija.appendChild(document.createElement("br"));
                var txtLokacija = document.createElement('input');
                txtLokacija.setAttribute("type", "text");
                txtLokacija.style.height = "40px";
                txtLokacija.style.width = "250px";
                txtLokacija.classList.add("input--style-4");
                varLokacija.appendChild(txtLokacija);
                prviRed.appendChild(varLokacija);

                var varDan = document.createElement("var");
                varDan.classList.add("col-2", "input-group");
                var dan = document.createTextNode("Dan");
                varDan.appendChild(dan);
                varDan.appendChild(document.createElement("br"));
                var txtDan = document.createElement('input');
                txtDan.setAttribute("type", "date");
                txtDan.style.height = "40px";
                txtDan.style.width = "250px";
                txtDan.style.margin = "auto";
                txtDan.classList.add("input--style-4");
                varDan.appendChild(txtDan);
                prviRed.appendChild(varDan);

                var varOcjena = document.createElement("var");
                varOcjena.classList.add("col-2", "input-group");
                var ocjena = document.createTextNode("Minimalna ocena");
                varOcjena.appendChild(ocjena);
                varOcjena.appendChild(document.createElement("br"));
                var txtOcjena = document.createElement('input');
                txtOcjena.min = 1;
                txtOcjena.max = 5;
                txtOcjena.setAttribute("type", "number");
                txtOcjena.style.height = "40px";
                txtOcjena.style.width = "250px";
                txtOcjena.classList.add("input--style-4");
                varOcjena.appendChild(txtOcjena);
                prviRed.appendChild(varOcjena);


                document.getElementById("content").appendChild(prviRed);

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