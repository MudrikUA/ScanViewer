let clockTimer = setInterval(
        function updateEventCount() {
            $.get("clock", function (fragment) { // get from controller
                $("#clock").replaceWith(fragment); // update snippet of page
            });
        }
, 1000);
let bugTimer = setInterval(
        function updateEventCount() {
            $.get("bugLiveUpdate", function (fragment) { // get from controller
                $("#bugScansTbl").replaceWith(fragment); // update snippet of page
            });
        }
, 3000);

let duplTimer = setInterval(
        function updateEventCount() {
            $.get("duplLiveUpdate", function (fragment) { // get from controller
                $("#duplScansTbl").replaceWith(fragment); // update snippet of page
            });
        }
, 2000);

function performSelectedDupl() {
    $.post("performCuurentDupl");
}

clockTimer
bugTimer
duplTimer