/**
 * PACKAGE : public.js
 * USER    : Kudzai Sean Huni.
 * TIME    : 10:47
 * DATE    : Monday-July-2018
 */

$(document).ready(
    function () {
        $("#btnRegisterUser").click(
            function (e) {
                e.preventDefault();
                var jsonObjStr = JSONifyREQ();
                $.post('/register/user', {
                    data: jsonObjStr
                }, function (respObj) {
                    if (respObj.isSuccess == true) {
                        showSuccessPopup("Email Sent!", respObj.respMsg);
                        clearRegistrationFormData();
                    } else {
                        showErrPopup("System Failure", "Unexpected response from the system.");
                    }
                });
            }
        );
    }
);