/**
 * PACKAGE : public.js
 * USER    : Kudzai Sean Huni.
 * TIME    : 10:48
 * DATE    : 02-July-2018
 */

var popupErrorTitle = "";
var jsonErrMsg = "";
var errMsg = "";
var isError = false;
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
                errorProcessor();
            }
        );
    }
);

/*]]>*/

function errorProcessor() {

    if (isError) {
        errMsg = extractErrObj(jsonErrMsg);

        var errMsg3 = extractErrMsg3(errMsg);

        var keyMappings = extractKeyMappings(errMsg);

        console.log("keyMappings Extracted: \n", keyMappings);

        var reqParams = buildReqParams(keyMappings);

        var hMap = ajaxCall("http://localhost:8080/register/messageSourceList?" + reqParams);

        console.log("Map-Data: ", hMap);

        var errorMsg = replaceErrorCodesWithMsgs(errMsg3, keyMappings, hMap);

        console.log("Extract Replaced: \n", errorMsg);

        showErrPopup(popupErrorTitle, errorMsg)
    }
    isError = false;
}

/**
 * Strip Special characters.
 * @param errMsg Error Message to be Stripped.
 * @returns {*}
 */
function extractErrMsg3(errMsg) {
    var errMsg2 = errMsg.replace(/,/g, "<br/>");
    var errMsg3 = errMsg2.replace(/{/g, "");
    errMsg3 = errMsg3.replace(/}/g, "");

    return errMsg3;
}

/**
 * Extracts Key-Mappings for the error-codes.
 * @param errMsg
 * @returns {*}
 */
function extractKeyMappings(errMsg) {
    return extractKeyErrorMsgs(errMsg, "{", "}");
}


function ajxUsrRegister(jsonObjStr) {

    $.ajax({
        type: 'POST',
        url: '/register/user',
        dataType: 'json',
        contentType: 'application/json',
        async: false,
        data: jsonObjStr,
        cache: false,
        timeout: 10000,
        success: function (data) {
            console.log("SUCCESS : ", data);
            clearRegistrationFormData();
            console.log("Form-Data Cleared...");
            $('#registrationModal').modal('hide');
            showSuccessPopup("Email Confirmation", data.message);
            $("#btnRegisterUser").prop("disabled", false);
        },
        error: function (error) {
            if (error.status === 400) {
                isError = true;
                popupErrorTitle = titleErrPopupMsg("Validation Error", error.responseJSON.errors.length);
                jsonErrMsg = error;
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
    var mapData;
    $.ajax({
        type: 'GET',
        url: urlWithData,
        dataType: 'json',
        contentType: 'application/json',
        async: false,
        cache: false,
        timeout: 10000,
        success: function (data) {
            var getResp = new Map();
            $.each(data, function (key, val) {
                console.log("Key: " + key + "\nVal: " + val);
                getResp.set(key, val);
                mapData = getResp;
                $('#map').hide().append("<li id='" + key + "'>" + val + "</li>");
            });
        },
        error: function (error) {
            console.log("Key-Mappings Error: ", error);
            showErrPopup("System Failure", "Unexpected response from the system.<br/> Failed to get Reason-Codes.");
            $("#btnRegisterUser").prop("disabled", false);
        }
    });

    return mapData;
}

function findSourceMatch(source, hMap) {
    var vResp = "";

    if (hMap.has(source)) {
        vResp = hMap.get(source);
    }
    return vResp;
}


function buildReqParams(codes) {
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

    return codesUrl;
}


function titleErrPopupMsg(errTitle, errorsLength) {
    if (errorsLength > 1) {
        return errTitle + "s";
    }
}

function extractErrObj(errorsObj) {

    var errMsg = "";
    $.each(errorsObj.responseJSON.errors, function (index, item) {
        errMsg += item.defaultMessage + "<br/>";
    });

    return errMsg;
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


function replaceErrorCodesWithMsgs(strMsg, arrayCodes, hMap) {
    var newMsgs = strMsg;

    $.each(arrayCodes, function (index, item) {
        var re = new RegExp(item.toString(), "ig");
        // $("#msgSourceId").text(item.toString());
        var sourceMsg = findSourceMatch(item.toString(), hMap);
        newMsgs = newMsgs.replace(re, sourceMsg);
    });

    return newMsgs;
}
