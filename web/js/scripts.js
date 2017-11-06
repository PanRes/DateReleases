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

    $(document).ready(function(){
        var itaImgLink = "http://www.roemheld.de/IT/Data/Images/Address/Italien.gif";
        var engImgLink = "http://www.roemheld.de/IT/Data/Images/Address/Grossbritanien.gif";
        var deuImgLink = "http://www.roemheld.de/IT/Data/Images/Address/Deutschland.gif";
        var fraImgLink = "http://www.roemheld.de/IT/Data/Images/Address/Frankreich.gif";

        var imgBtnSel = $('#imgBtnSel');
        var imgBtnIta = $('#imgBtnIta');
        var imgBtnEng = $('#imgBtnEng');
        var imgBtnDeu = $('#imgBtnDeu');
        var imgBtnFra = $('#imgBtnFra');

        var imgNavSel = $('#imgNavSel');
        var imgNavIta = $('#imgNavIta');
        var imgNavEng = $('#imgNavEng');
        var imgNavDeu = $('#imgNavDeu');
        var imgNavFra = $('#imgNavFra');

        var spanNavSel = $('#lanNavSel');
        var spanBtnSel = $('#lanBtnSel');

        imgBtnSel.attr("src",itaImgLink);
        imgBtnIta.attr("src",itaImgLink);
        imgBtnEng.attr("src",engImgLink);
        imgBtnDeu.attr("src",deuImgLink);
        imgBtnFra.attr("src",fraImgLink);

        imgNavSel.attr("src",itaImgLink);
        imgNavIta.attr("src",itaImgLink);
        imgNavEng.attr("src",engImgLink);
        imgNavDeu.attr("src",deuImgLink);
        imgNavFra.attr("src",fraImgLink);

        $( ".language" ).on( "click", function( event ) {
            var currentId = $(this).attr('id');

            if(currentId == "navIta") {
                imgNavSel.attr("src",itaImgLink);
                spanNavSel.text("ITA");
            } else if (currentId == "navEng") {
                imgNavSel.attr("src",engImgLink);
                spanNavSel.text("ENG");
            } else if (currentId == "navDeu") {
                imgNavSel.attr("src",deuImgLink);
                spanNavSel.text("DEU");
            } else if (currentId == "navFra") {
                imgNavSel.attr("src",fraImgLink);
                spanNavSel.text("FRA");
            }

            if(currentId == "btnIta") {
                imgBtnSel.attr("src",itaImgLink);
                spanBtnSel.text("ITA");
            } else if (currentId == "btnEng") {
                imgBtnSel.attr("src",engImgLink);
                spanBtnSel.text("ENG");
            } else if (currentId == "btnDeu") {
                imgBtnSel.attr("src",deuImgLink);
                spanBtnSel.text("DEU");
            } else if (currentId == "btnFra") {
                imgBtnSel.attr("src",fraImgLink);
                spanBtnSel.text("FRA");
            }

        });
    });
});

$(function(){
    $('.selectpicker').selectpicker();
});