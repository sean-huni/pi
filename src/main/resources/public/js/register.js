/**
 * PACKAGE : public.js
 * USER    : Kudzai Sean Huni.
 * TIME    : 10:48
 * DATE    : Monday-July-2018
 */

/*<![CDATA[*/

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
                clearRegistrationFormData();
                console.log("Form-Data Cleared...");
                $('#registrationModal').modal('hide');
                showSuccessPopup("Email Confirmation", data.message);
            } else {
                showErrPopup("Unexpected Response", "Unexpected response from the system.");
            }
            $("#btnRegisterUser").prop("disabled", false);
        },
        error: function (error) {
            if (error.status === 400) {
                var popupErrorTitle = titleErrPopupMsg("Validation Error", error.responseJSON.errors.length);
                var popupErrMsg = appendErrMessages(error);

                showErrPopup(popupErrorTitle, popupErrMsg)
            } else {
                showErrPopup("System Failure", "Unexpected response from the system.");
            }

            console.log("Error: ", error);
            $("#btnRegisterUser").prop("disabled", false);
        }
    });

    function titleErrPopupMsg(errTitle, errorsLength) {
        if (errorsLength > 1) {
            return errTitle + "s";
        }
    }

    function appendErrMessages(errorsObj) {
        // var errors = $.parseJSON(errorsObj.message);

        console.log(/*[[#{Size.userDto.firstName.min}]]*/);

        var errMsg = "";
        $.each(errorsObj.responseJSON.errors, function (index, item) {
            errMsg += item.defaultMessage + "<br/>";
        });
        var errMsg2 = errMsg.replace(/,/g, "<br/>");
        var errMsg3 = errMsg2.replace(/{/g, "[[#{");
        errMsg3 = errMsg3.replace(/}/g, "}]]");
        console.log(errMsg3);
        return errMsg2;

    }
}

/* ]]>*/