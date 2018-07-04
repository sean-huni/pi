/**
 * PACKAGE : public.js
 * USER    : Kudzai Sean Huni.
 * TIME    : 10:48
 * DATE    : Monday-July-2018
 */


$(document).ready(
    function () {

        $("#btnRegisterUser").click(
            function (e) {
                var jsonObjStr = JSONifyREQ();

                e.preventDefault();
                $("#btnRegisterUser").prop("disabled", true);

                console.log("JSON REQ: ", jsonObjStr);
                ajxUsrRegister(jsonObjStr);
            }
        );
    }
);


function ajxUsrRegister(jsonObjStr) {
    $.ajax({
        type: 'POST',
        url: '/register/user',
        dataType: 'json',
        contentType: 'application/json;charset=utf-8',
        data: jsonObjStr,
        cache: false,
        timeout: 10000,
        success: function (data) {
            console.log("SUCCESS : ", data);
            if (data.isSuccess == true) {
                showSuccessPopup("Registration Email Sent!", data.message);
                clearRegistrationFormData();
            } else {
                showErrPopup("Unexpected Response", "Unexpected response from the system.");
            }
            $("#btnRegisterUser").prop("disabled", false);
        },
        error: function (error) {
            showErrPopup("System Failure", "Unexpected response from the system.");
            console.log("Error: ", error);
            $("#btnRegisterUser").prop("disabled", false);
        }
    });
}