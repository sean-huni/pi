/**
 * PACKAGE : public.js
 * USER    : Kudzai Sean Huni.
 * TIME    : 10:48
 * DATE    : Monday-July-2018
 */

var map = {};
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

/*]]>*/

function ajxUsrRegister(jsonObjStr) {
    $.ajax({
        type: 'POST',
        url: '/register/user',
        dataType: 'json',
        contentType: 'application/json',
        data: jsonObjStr,
        cache: false,
        timeout: 10000,
        success: function (data) {
            console.log("SUCCESS : ", data);
            // if (data.isSuccess == true) {
            clearRegistrationFormData();
            console.log("Form-Data Cleared...");
            $('#registrationModal').modal('hide');
            showSuccessPopup("Email Confirmation", data.message);
            // } else {
            //     showErrPopup("Unexpected Response", "Unexpected response from the system.");
            // }
            $("#btnRegisterUser").prop("disabled", false);
        },
        error: function (error) {
            if (error.status === 400) {
                var popupErrorTitle = titleErrPopupMsg("Validation Error", error.responseJSON.errors.length);
                var popupErrMsg = appendErrMessages(error);

                showErrPopup(popupErrorTitle, popupErrMsg)
            } else {
                console.log("Error: \n" + error.responseJSON);
                showErrPopup("System Failure", "Unexpected response from the system.");
            }

            // console.log("Error: ", error);
            $("#btnRegisterUser").prop("disabled", false);
        }
    });
}


function ajaxCall(urlWithData) {

    $.ajax({
        type: 'GET',
        url: urlWithData,
        dataType: 'json',
        contentType: 'application/json',
        cache: false,
        timeout: 10000,
        success: function (data) {
            var getResp = new Map();
            $.each(data, function (key, val) {
                console.log("Key: " + key + "\nVal: " + val);
                getResp.set(key, val);
                $('#map').hide().append("<li id='" + key + "'>" + val + "</li>");
            });
        },
        error: function (error) {
            showErrPopup("System Failure", "Unexpected response from the system.");
            $("#btnRegisterUser").prop("disabled", false);
        }
    });


}

function findSourceMatch(source) {
    var vResp = "";
    // $('#map li').each(function () {
    //     if ($(this).attr('id') === source) {
    //         // $(this).attr('id');
    //         return $(this).text();
    //     } else {
    //         console.log("MAP is Null/Empty...");
    //     }
    //     return "";
    // });

    for (var i = 0; i < $('#map li').length; i++) {
        if ($('#map li').eq(i).attr('id') == source) {
            vResp = $('#map li').eq(i).text();
        }
    }

    return vResp;
}


function messageSourceSubstitute(codes) {
    var codesUrl = "";
    // var resp = new Map();
    var firstIter = true;

    $.each(codes, function (index, item) {
        if (firstIter) {
            codesUrl += "code=" + item;
            firstIter = false;
        } else {
            codesUrl += "&code=" + item;
        }
    });

    ajaxCall("http://localhost:8080/register/messageSourceList?" + codesUrl);


    // $.get("http://localhost:8080/register/messageSourceList?" + codesUrl,
    //     {async: false},
    //     function (data, status, xhr) {
    //         var getResp = new Map();
    //         $.each(data, function (key, val) {
    //             console.log("Key: " + key + "\nVal: " + val);
    //             getResp.set(key, val);
    //         });
    //
    //         resp = getResp;
    //     }).done(function () {
    //     alert(resp);
    // });
}


function titleErrPopupMsg(errTitle, errorsLength) {
    if (errorsLength > 1) {
        return errTitle + "s";
    }
}

function appendErrMessages(errorsObj) {

    var errMsg = "";
    $.each(errorsObj.responseJSON.errors, function (index, item) {
        errMsg += item.defaultMessage + "<br/>";
    });
    var errMsg2 = errMsg.replace(/,/g, "<br/>");
    var errMsg3 = errMsg2.replace(/{/g, "");
    errMsg3 = errMsg3.replace(/}/g, "");
    console.log("Msg: \n", errMsg3);

    var keyMappings = extractKeyErrorMsgs(errMsg2, "{", "}");
    console.log("Extract: \n", keyMappings);
    var errorMsg = replaceErrorCodesWithMsgs(errMsg3, keyMappings);
    console.log("Extract Replaced: \n", errorMsg);
    return errorMsg;
}

function extractKeyErrorMsgs(str, start, end) {

    var getFromBetween = {
        results: [],
        string: "",
        getFromBetween: function (sub1, sub2) {
            if (this.string.indexOf(sub1) < 0 || this.string.indexOf(sub2) < 0) return false;
            var SP = this.string.indexOf(sub1) + sub1.length;
            var string1 = this.string.substr(0, SP);
            var string2 = this.string.substr(SP);
            var TP = string1.length + string2.indexOf(sub2);
            return this.string.substring(SP, TP);
        },
        removeFromBetween: function (sub1, sub2) {
            if (this.string.indexOf(sub1) < 0 || this.string.indexOf(sub2) < 0) return false;
            var removal = sub1 + this.getFromBetween(sub1, sub2) + sub2;
            this.string = this.string.replace(removal, "");
        },
        getAllResults: function (sub1, sub2) {
            // first check to see if we do have both substrings
            if (this.string.indexOf(sub1) < 0 || this.string.indexOf(sub2) < 0) return;

            // find one result
            var result = this.getFromBetween(sub1, sub2);
            // push it to the results array
            this.results.push(result);
            // remove the most recently found one from the string
            this.removeFromBetween(sub1, sub2);

            // if there's more substrings
            if (this.string.indexOf(sub1) > -1 && this.string.indexOf(sub2) > -1) {
                this.getAllResults(sub1, sub2);
            }
            else return;
        },
        get: function (string, sub1, sub2) {
            this.results = [];
            this.string = string;
            this.getAllResults(sub1, sub2);
            return this.results;
        }
    };

    var errorKeys = getFromBetween.get(str, start, end);

    return errorKeys;
}


function replaceErrorCodesWithMsgs(strMsg, arrayCodes) {
    var newMsgs = strMsg;

    messageSourceSubstitute(arrayCodes);

    $.each(arrayCodes, function (index, item) {
        var re = new RegExp(item.toString(), "ig");
        $("#msgSourceId").text(item.toString());
        newMsgs = newMsgs.replace(re, findSourceMatch(item.toString())); //FixMe replace match with messageSource.
    });

    return newMsgs;
}
