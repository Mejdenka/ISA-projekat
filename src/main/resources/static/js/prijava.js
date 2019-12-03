$(document).ready(function(){
    $('#formaZaPrijavu').submit(function(event){
        event.preventDefault();

        var username=$('#username').val();
        var password=$('#password').val();

        if(username === "" || password === ""){
            alert("Nijedno polje ne sme ostati prazno!")
            return
        }

        $.post({
            url: 'api/korisnici/login',
            data: JSON.stringify({username, password}),
            contentType: 'application/json',
            success: function(data) {
                alert('Prijavili ste se!');
                //window.location='pocetna.html';
                localStorage.setItem('jwt',data)
                localStorage.getItem('jwt')
                console.log(data);
                console.log("to je jwt");

            },
            error: function() {
                alert("Neuspešna prijava.")
            }
        });

    });
});