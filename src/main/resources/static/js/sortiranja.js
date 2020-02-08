
function sort_jbo()
{
    return function () {

        var table=$('#tabelaPacijenata');
        var tbody =$('#tabelaPacijenataBody');

        tbody.find('tr').sort(function(a, b)
        {
            if($('#jbo_order').val()=='asc')
            {
                return $('td:nth-child(3)', a).text().localeCompare($('td:nth-child(3)', b).text());
            }
            else
            {
                return $('td:nth-child(3)', b).text().localeCompare($('td:nth-child(3)', a).text());
            }

        }).appendTo(tbody);

        var sort_order=$('#jbo_order').val();
        if(sort_order=="asc")
        {
            document.getElementById("jbo_order").value="desc";
        }
        if(sort_order=="desc")
        {
            document.getElementById("jbo_order").value="asc";
        }
    }
}

function sort_ime()
{
    return function () {

        var tbody =$('#tabelaPacijenataBody');

        tbody.find('tr').sort(function(a, b)
        {
            if($('#name_order').val()=='asc')
            {
                return $('td:first', a).text().localeCompare($('td:first', b).text());
            }
            else
            {
                return $('td:first', b).text().localeCompare($('td:first', a).text());
            }

        }).appendTo(tbody);

        var sort_order=$('#name_order').val();
        if(sort_order=="asc")
        {
            document.getElementById("name_order").value="desc";
        }
        if(sort_order=="desc")
        {
            document.getElementById("name_order").value="asc";
        }
    }
}


function sort_prezime()
{
    return function () {

        var tbody =$('#tabelaPacijenataBody');

        tbody.find('tr').sort(function(a, b)
        {
            if($('#prezime_order').val()=='asc')
            {
                return $('td:nth-child(2)', a).text().localeCompare($('td:nth-child(2)', b).text());
            }
            else
            {
                return $('td:nth-child(2)', b).text().localeCompare($('td:nth-child(2)', a).text());
            }

        }).appendTo(tbody);

        var sort_order=$('#prezime_order').val();
        if(sort_order=="asc")
        {
            document.getElementById("prezime_order").value="desc";
        }
        if(sort_order=="desc")
        {
            document.getElementById("prezime_order").value="asc";
        }
    }
}

function sort_email()
{
    return function () {

        var tbody =$('#tabelaPacijenataBody');

        tbody.find('tr').sort(function(a, b)
        {
            if($('#email_order').val()=='asc')
            {
                return $('td:nth-child(3)', a).text().localeCompare($('td:nth-child(3)', b).text());
            }
            else
            {
                return $('td:nth-child(3)', b).text().localeCompare($('td:nth-child(3)', a).text());
            }

        }).appendTo(tbody);

        var sort_order=$('#email_order').val();
        if(sort_order=="asc")
        {
            document.getElementById("email_order").value="desc";
        }
        if(sort_order=="desc")
        {
            document.getElementById("email_order").value="asc";
        }
    }
}