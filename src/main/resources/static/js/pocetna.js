$(document).ready(function(){
        $.get({
            url: 'api/korisnici/'+"admin"+'/',
            contentType: 'application/json',
            success: function(korisnik) {
                console.log(korisnik.ime)
                console.log(korisnik.prezime)
                console.log(korisnik.email())
                console.log(korisnik.datumRodjenja)
                console.log(korisnik.korisnickoIme)
            },
            error: function() {
                alert("Neuspešno.")
            }
        });
});