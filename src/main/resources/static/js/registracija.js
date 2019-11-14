$(document).ready(function(){
    $('#formaZaRegistraciju').submit(function(event){
        event.preventDefault();


        var korisnickoIme=$('#username').val();
        var lozinka=$('#password').val();
        var ime=$('#first_name').val();
        var prezime=$('#last_name').val();
        var email=$('#email').val();
        var datumRodjenja=$('#birthday').val();

        if(korisnickoIme === "" || lozinka === "" || ime === "" || prezime === "" || email === "" || datumRodjenja === ""){
            alert("Nijedno polje ne sme ostati prazno!")
            return
        }


        $.post({
            url: 'api/pacijenti',
            data: JSON.stringify({korisnickoIme, lozinka, ime, prezime, email, datumRodjenja}),
            contentType: 'application/json',
            success: function() {
                alert('Registrovali ste se!');
            },
            error: function() {
                alert("Neuspešna registracija.")
            }
        });
    });
});