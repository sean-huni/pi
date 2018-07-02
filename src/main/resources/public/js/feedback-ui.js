function showSuccess(msg) {
    $('#errorbox').hide();
    $('#successhtml').html(msg);
    $('#successbox').show('slow');
    $('html, body').animate({scrollTop: 0}, 'fast');
}

function showError(errorhtml) {
    if (arguments.length == 3) {
        showErrorPopup(arguments[0], arguments[1], arguments[2]);
    } else {
        $('#successbox').hide();
        $('#errorhtml').html(errorhtml);
        $("#errorbox").show("slow");
        $('html, body').animate({scrollTop: 0}, 'fast');
    }
}

function showInfo(infohtml) {
    $('#infobox').hide();
    $('#infobox').html(infohtml);
    $("#infobox").show("slow");
    $('html, body').animate({scrollTop: 0}, 'fast');
}

function hideFeedback() {
    $('#errorbox').hide();
    $('#successbox').hide();
    $('#infobox').hide();
}

function clearErrors() {
    $("#errorbox").hide();
    $(".error").each(function () {
        $(this).removeClass('error');
    });
}

function errorProc(responseObj) {
    showError(responseObj.message);
    showErrorFields(responseObj.errors);
}

$(document).ready(function () {
    $('.alert .close').live("click", function (e) {
        $(this).parent().hide();
    });
});