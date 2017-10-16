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