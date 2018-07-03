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
                e.preventDefault();
                var jsonObjStr = JSONifyREQ();
                console.log(jsonObjStr);
                ajxUsrRegister(jsonObjStr);
                // $.post('/register/user', {
                //     dataType: "json",
                //     contentType: "application/json;charset=UTF-8",
                //     // headers: {
                //     //     'Content-Type': 'application/json;charset=UTF-8'
                //     // },
                //     data: jsonObjStr
                // }, function (data, status) {
                //     if (data.isSuccess == true) {
                //         showSuccessPopup("Registration Email Sent!", data.message);
                //         clearRegistrationFormData();
                //     } else {
                //         showErrPopup("System Failure", "Unexpected response from the system.");
                //     }
                // });
            }
        );
    }
);


function ajxUsrRegister(jsonObjStr) {
    // $.ajax({
    //     type: "POST",
    //     contentType: 'application/json',
    //     url: '/register/user',
    //     dataType: 'json',
    //     async: false,
    //     data: jsonObjStr,
    //     success: function (respObj) {
    //         if (respObj.isSuccess == true) {
    //             showSuccessPopup("Registration Email Sent!", respObj.message);
    //             clearRegistrationFormData();
    //         } else {
    //             showErrPopup("System Failure", "Unexpected response from the system.");
    //         }
    //     }
    // });


    $.ajax({
        url: '/register/user',
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: jsonObjStr,
        success: function (data, status, jQxhr) {
            if (data.isSuccess == true) {
                showSuccessPopup("Registration Email Sent!", data.message);
                clearRegistrationFormData();
            } else {
                showErrPopup("System Failure", "Unexpected response from the system.");
            }
        },
        error: function (jqXhr, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });
}