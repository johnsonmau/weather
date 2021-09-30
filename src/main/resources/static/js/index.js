$(document).ready(function () {

    startParticles()

    $( "#currentLocButton" ).click(function( event ) {
        event.preventDefault();
        showSpinner()
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
        showSpinner();
        window.location.href = "/home?location=" + $(this).text()
    });

    $('#location').keypress(function (e) {
        if (e.which == 13) {
            if ($('#location').val() == '') processInputLocation(e)
            else {
                showSpinner()
            }
        }
    });

    $( "#getWeatherLink" ).click(function( event ) {
        processInputLocation(event)
    });

})

function processInputLocation(event){
    event.preventDefault();
    if ($('#location').val().trim() == ''){
        document.getElementById("location").className = document.getElementById("location").className + " customError";
    } else {
        document.getElementById("location").className = document.getElementById("location").className
            .replace(" customError", "");
        showSpinner()
        $("#currentLocButton").prop('disabled', true);
        $("#getWeatherLink").prop('disabled', true);
        window.location.href = "/home?location=" + $('#location').val()
    }
}

function showSpinner() {
    $("#searchSpinner").prop('hidden', false);
}
function hideSpinner() {
    $("#searchSpinner").prop('hidden', true);
}

function startParticles() {
    Particles.init({
        selector: '.background',
        maxParticles: 650,
        color: '#81B29A'
    });
}


function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition, positionFailed);
    } else {
        alert("Sorry, could not get current location.")
    }
}

function showPosition(position) {
    window.location.href = "/home?location="+position.coords.longitude+","+position.coords.latitude;
}

function positionFailed(error) {
    hideSpinner()
    if (error.message == "User denied Geolocation"){
        alert("You must enable location access for this application to get your current location." +
            " If you wish not to, you may enter a location manually.")
    } else {
        alert("Sorry, could not get current location.")
    }
}
