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
            url: 'api/pacijenti/'+username+'/'+password,
            contentType: 'application/json',
            success: function() {
                alert('Prijavili ste se!');
            },
            error: function() {
                alert("Neuspešna prijava.")
            }
        });
    });
});