$(document).ready(function () {

    $( "#currentLocButton" ).click(function( event ) {
        event.preventDefault();
        $("#searchSpinner").prop('hidden', false);
        getLocation();
    });

    $( "#mainTitle" ).click(function( event ) {
        event.preventDefault();
        window.location.href ="/"
    });

    $(".collapse").on('shown.bs.collapse', function(){
        document.getElementById("showRecentLoc").innerHTML = 'Hide recently searched locations'
    });
    $(".collapse").on('hidden.bs.collapse', function(){
        document.getElementById("showRecentLoc").innerHTML = 'Show recently searched locations'
    });

    $( ".recentLoc" ).click(function( event ) {
        event.preventDefault();
        $(".recentLoc").prop('disabled', true);
        $("#searchSpinner").prop('hidden', false);
        window.location.href = "/home?location=" + $(this).text()
    });

    $( "#getWeatherLink" ).click(function( event ) {
        $("#noInputErr").prop('hidden', true);
        event.preventDefault();
        if ($('#location').val().trim() == ''){
            $("#noInputErr").prop('hidden', false);
        } else {
            $("#searchSpinner").prop('hidden', false);
            $("#currentLocButton").prop('disabled', true);
            $("#getWeatherLink").prop('disabled', true);
            window.location.href = "/home?location=" + $('#location').val()
        }
    });

})

function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
    } else {
        console.log("Geolocation is not supported by this browser.");
    }
}

function showPosition(position) {
    window.location.href = "/home?location="+position.coords.longitude+","+position.coords.latitude;
}
