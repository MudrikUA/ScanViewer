let clockTimer = setInterval(
        function updateEventCount() {
            $.get("clock", function (fragment) { // get from controller
                $("#clock").replaceWith(fragment); // update snippet of page
            });
        }
, 1000);