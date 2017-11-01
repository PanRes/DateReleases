$( document ).ready(function() {

    //for editUserInfo.jsp to preview image
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#profileImg').attr('src', e.target.result);
            }

            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#profileImg").change(function(){
        readURL(this);
    });

    //for addDateForms.jsp to activate button when xlsx is chosen
    $("#uploadXlsx").change(function() {
        $("#xlsxSubmitBtn").removeClass("disabled");
        $("#xlsxSubmitBtn").prop("disabled",false);
    });

    $("#season").change(function() {
        if(($("#season").val().length > 0) && ($("#episode").val().length > 0) && ($("#date").val().length > 0)){
            $("#addSingleEpisodeBtn").removeClass("disabled");
            $("#addSingleEpisodeBtn").prop("disabled",false);
        }
    });

    $("#episode").change(function() {
        if(($("#season").val().length > 0) && ($("#episode").val().length > 0) && ($("#date").val().length > 0)){
            $("#addSingleEpisodeBtn").removeClass("disabled");
            $("#addSingleEpisodeBtn").prop("disabled",false);
        }
    });

    $("#date").change(function() {
        if(($("#season").val().length > 0) && ($("#episode").val().length > 0) && ($("#date").val().length > 0)){
            $("#addSingleEpisodeBtn").removeClass("disabled");
            $("#addSingleEpisodeBtn").prop("disabled",false);
        }
    });
});