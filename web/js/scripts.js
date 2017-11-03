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

    $("#uploadProfileImage").change(function(){
        readURL(this);
    });

    //for addSeriesDateForms.jsp to activate button when xlsx is chosen
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

    $("#passwordVerify").change(function () {
        var verifyMessage = "New Passwords must be the same"
        if ($("#password").val() != "") {
            if($("#passwordVerify").val() != $("#password").val()){
                document.getElementById("verifyMessage").innerHTML = verifyMessage;
                $("#btnChangePassword").addClass("disabled");
                $("#btnChangePassword").prop("disabled",true);
            }
            else {
                document.getElementById("verifyMessage").innerHTML = "";
                $("#btnChangePassword").removeClass("disabled");
                $("#btnChangePassword").prop("disabled",false);
            }
        }
    });

    $("#password").change(function () {
        var verifyMessage = "New Passwords must be the same"
        if ($("#passwordVerify").val() != "") {
            if($("#passwordVerify").val() != $("#password").val()){
                document.getElementById("verifyMessage").innerHTML = verifyMessage;
                $("#btnChangePassword").addClass("disabled");
                $("#btnChangePassword").prop("disabled",true);
            }
            else {
                document.getElementById("verifyMessage").innerHTML = "";
                $("#btnChangePassword").removeClass("disabled");
                $("#btnChangePassword").prop("disabled",false);
            }
        }
    });
/*
    $("#passwordVerifySignUp").change(function () {
        console.log($("#passwordVerifySignUp").val());
        console.log($("#passwordSignUp").val());
        var verifyMessage = "Passwords must be the same"
        if ($("#passwordSignUp").val() != "") {
            if($("#passwordVerifySignUp").val() != $("#password").val()){
                document.getElementById("verifyMessageSignUp").innerHTML = verifyMessage;
                $("#btnSubmitSignUp").addClass("disabled");
                $("#btnSubmitSignUp").prop("disabled",true);
            }
            else {
                document.getElementById("verifyMessageSignUp").innerHTML = "";
                $("#btnSubmitSignUp").removeClass("disabled");
                $("#btnSubmitSignUp").prop("disabled",false);
            }
        }
    });

    $("#passwordSignUp").change(function () {
        console.log($("#passwordVerifySignUp").val());
        console.log($("#passwordSignUp").val());
        var verifyMessage = "Passwords must be the same"
        if ($("#passwordVerifySignUp").val() != "") {
            if($("#passwordVerifySignUp").val() != $("#passwordSignUp").val()){
                document.getElementById("verifyMessageSignUp").innerHTML = verifyMessage;
                $("#btnSubmitSignUp").addClass("disabled");
                $("#btnSubmitSignUp").prop("disabled",true);
            }
            else {
                document.getElementById("verifyMessageSignUp").innerHTML = "";
                $("#btnSubmitSignUp").removeClass("disabled");
                $("#btnSubmitSignUp").prop("disabled",false);
            }
        }
    });*/
});