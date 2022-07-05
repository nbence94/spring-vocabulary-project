 $(document).ready(function() {

    $('#openCreate').on('click',function(event) {
    	console.log("Szó hozzáadása modal")

        //Get the VocabularyId and give it to the hidden element
        const box = document.getElementById('vocid');
        $('#vocabId').val(box.innerText);


        $('#createModal').modal();
    });

    $('.openDelete').on('click',function(event) {
        console.log("Szó törlése")
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteModal #confirmDelete').attr('href', href);
        $('#deleteModal').modal();
    });

    $('.openUpdate').on('click',function(event) {
        console.log("Szó módosítása")
        event.preventDefault();

        var href = $(this).attr('href');
        $.get(href, function(word, status) {
            $('#idEdit').val(word.id);
            $('#hungarianEdit').val(word.hungarian);
            $('#englishEdit').val(word.english);
            $('#vocabIdEdit').val(word.vocabularyId);
        });

        $('#updateModal').modal();
    });
});
