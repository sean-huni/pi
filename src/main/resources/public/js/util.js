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


function JSONifyREQ() {
    var array = [];
    array.push({
        UserDTO: {
            firstName: $("#txtRegFirstName").val(),
            username: $("#txtRegFirstName").val(),
            pass: $("#txtRegFirstName").val(),
            pass2: $("#txtRegFirstName").val()
        }
    });
    return JSON.stringify(array);
}
