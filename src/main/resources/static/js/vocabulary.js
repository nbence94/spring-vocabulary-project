$(document).ready(function() {

    $('#openCreate').on('click',function(event) {
    	console.log("Szótár létrehozása")
        $('#createModal').modal();
    });

    $('.openDelete').on('click',function(event) {
        console.log("Szótár törlése")
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteModal #confirmDelete').attr('href', href);
        $('#deleteModal').modal();
    });
});
