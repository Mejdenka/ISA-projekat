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
            success: function(user) {
                alert('Registrovali ste se kao ' + user.username);
                window.location='prijava.html';
            },
            error: function() {
                alert("Neuspešna registracija.")
            }
        });
    });

    //ZA DATEPICKER
    try {
        $('.js-datepicker').daterangepicker({
            "singleDatePicker": true,
            "showDropdowns": true,
            "autoUpdateInput": false,
            locale: {
                format: 'DD/MM/YYYY'
            },
        });

        var myCalendar = $('.js-datepicker');
        var isClick = 0;

        $(window).on('click',function(){
            isClick = 0;
        });

        $(myCalendar).on('apply.daterangepicker',function(ev, picker){
            isClick = 0;
            $(this).val(picker.startDate.format('DD/MM/YYYY'));

        });

        $('.js-btn-calendar').on('click',function(e){
            e.stopPropagation();

            if(isClick === 1) isClick = 0;
            else if(isClick === 0) isClick = 1;

            if (isClick === 1) {
                myCalendar.focus();
            }
        });

        $(myCalendar).on('click',function(e){
            e.stopPropagation();
            isClick = 1;
        });

        $('.daterangepicker').on('click',function(e){
            e.stopPropagation();
        });


    } catch(er) {console.log(er);}
    /*[ Select 2 Config ]
        ===========================================================*/

    try {
        var selectSimple = $('.js-select-simple');

        selectSimple.each(function () {
            var that = $(this);
            var selectBox = that.find('select');
            var selectDropdown = that.find('.select-dropdown');
            selectBox.select2({
                dropdownParent: selectDropdown
            });
        });

    } catch (err) {
        console.log(err);
    }
});