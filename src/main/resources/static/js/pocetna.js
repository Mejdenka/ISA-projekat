$(document).ready(function(){
        $.get({
            url: 'api/korisnici/'+"proba"+'/',
            contentType: 'application/json',
            success: function(korisnik) {
                console.log(korisnik.uloga)
                switch (korisnik.uloga) {
                    case "PACIJENT": alert("Pacijent")
                }
            },
            error: function() {
                alert("Neuspešno.")
            }
        });
});