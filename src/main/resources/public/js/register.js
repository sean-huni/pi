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


/*]]>*/