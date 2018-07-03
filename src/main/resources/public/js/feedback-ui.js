/**
 * Shows a modal popup message, using the genericPopup div. A single OK button is presented to the user.
 * @param title     message box title
 * @param message   message content
 */
function showSuccessPopup(title, message) {
    $('#successTitle').text(title);
    $('#successMsg').html(message.replace("\n", "<br/>"));

    $('#successPopup').modal('show');
}


/**
 * Shows an error message popup. Box will have a red title.
 * @param title     message box title
 * @param message   message content
 */
function showErrPopup(title, message) {
    $('#errorTitle').text(title);
    $('#errorMsg').html(message != null ? message.replace("\n", "<br/>") : '');

    $('#errorPopup').modal('show');
}


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