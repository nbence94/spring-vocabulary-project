$(document).ready(function() {

    $('#openCreate').on('click',function(event) {
    	console.log("Szótár létrehozása")
        $('#createModal').modal();
    });

//Most épp azt csinálja, hogy hiába nyomsz rá az Igen gombra, nem történi semmi
    $('.openDelete').on('click',function(event) {
        console.log("Szótár törlése")
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteModal #confirmDelete').attr('href', href);
        $('#deleteModal').modal();
    });
});
