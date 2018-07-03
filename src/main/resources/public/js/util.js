// Logout Function
function logoutSubmit() {
    document.getElementById("logoutForm").submit();
}

//User Registration
function registerUserSubmit() {
    document.getElementById("registrationForm").submit();
}


//Password Reset
function passwordReset() {
    document.getElementById("forgotPasswordForm").submit();
}


function clearRegistrationFormData() {
    $("#txtRegFirstName").val("");
    $("#txtRegUsername").val("");
    $("#txtRegPass").val("");
    $("#txtRegPass2").val("");
}

/**
 * Builds a JSON Request with the required parameters for registration.
 * @return {string}
 * @constructor
 */
function JSONifyREQ() {
    var array = [];
    array.push({
        // userDTO: {
            firstName: $("#txtRegFirstName").val(),
            username: $("#txtRegUsername").val(),
            pass: $("#txtRegPassword").val(),
            pass2: $("#txtRegPassword2").val()
        // }
    });
    return JSON.stringify(array).replace("]", "").replace("[", "");
}

/**
 * Delete the last character in a given string.
 * @param strVal
 * @return {*}
 */
function removeLastChar(strVal) {
    return strVal.slice(0, -1);
}

/**
 * Delete the 1st character in a given string.
 * @param strVal
 * @return {*}
 */
function remove1stChar(strVal) {
    return strVal.substring(1);
}
