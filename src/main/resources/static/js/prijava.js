$(document).ready(function(){
    $('#formaZaPrijavu').submit(function(event){
        event.preventDefault();

        var korisnickoIme=$('#username').val();
        var lozinka=$('#password').val();

        if(korisnickoIme === "" || lozinka === ""){
            alert("Nijedno polje ne sme ostati prazno!")
            return
        }

        $.get({
            url: 'api/pacijenti/'+korisnickoIme+'/'+lozinka,
            contentType: 'application/json',
            success: function() {
                alert('Prijavili ste se!');
                window.location='homepage_pacijent.html';
            },
            error: function() {
                alert("Neuspešna prijava.")
            }
        });
    });
});