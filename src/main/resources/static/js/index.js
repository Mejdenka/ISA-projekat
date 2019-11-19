$(document).ready(function(){
    //Predefinisani admin
    $.post({
        url: 'api/adminKC',
        data: JSON.stringify({ime : "predef", prezime : "predef", korisnickoIme: "predef", lozinka: "predef"}),
        contentType: 'application/json',
        success: function() {
            console.log("Napravljen predefinisani admin.")
        },
        error: function() {
            console.log("Greska kod pravjenja predefinisanog admina. Admin vec postoji u bazi.")
        }
    });
});