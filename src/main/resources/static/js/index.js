$(document).ready(function () {

    showOrHideMap()

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

    openCollapseAndChangeButtonText("recentLocCollapse", "showRecentLoc", "Hide recently searched locations")
    closeCollapseAndChangeButtonText("recentLocCollapse", "showRecentLoc", "Show recently searched locations")

    openCollapseAndChangeButtonText("sevenDayDivCollapse", "toggleDailyForecast", "Hide 7 Day Forecast")
    closeCollapseAndChangeButtonText("sevenDayDivCollapse", "toggleDailyForecast", "Show 7 Day Forecast")

    openCollapseAndChangeButtonText("hourlyDivCollapse", "toggleHourlyForecast", "Hide Hourly Forecast")
    closeCollapseAndChangeButtonText("hourlyDivCollapse", "toggleHourlyForecast", "Show Hourly Forecast")

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

function openCollapseAndChangeButtonText(collapseName, buttonElement, buttonText){
    $("#"+collapseName).on('shown.bs.collapse', function(){
        changeButtonText(buttonElement, buttonText)
    });
}

function closeCollapseAndChangeButtonText(collapseName, buttonElement, buttonText){
    $("#"+collapseName).on('hidden.bs.collapse', function(){
        changeButtonText(buttonElement, buttonText)
    });
}

function changeButtonText(buttonElement, buttonText){
    document.getElementById(buttonElement).innerHTML = buttonText
}

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
        maxParticles: 600,
        color: '#2D82B7'
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

function showOrHideMap() {
    if (latitude == null && longitude == null){
        hideMap()
    } else {
        createMap()
        showMap()
    }
}

function showMap(){
    $("#map").prop('hidden', false);
    $("#mapTitle").prop('hidden', false);

}
function hideMap(){
    $("#map").prop('hidden', true);
    $("#mapTitle").prop('hidden', true);
}

function createMap(){
    require(["esri/config","esri/Map", "esri/views/MapView","esri/Graphic", "esri/layers/GraphicsLayer", "esri/layers/MapImageLayer",
        "esri/widgets/Expand", "esri/widgets/Legend", "esri/widgets/Locate"], function (esriConfig,Map, MapView, Graphic, GraphicsLayer, MapImageLayer, Expand, Legend, Locate) {

        esriConfig.apiKey = "AAPK3d306b43e5fa42ada2e3125497faf887gziP6G0vj1clFelPABS4PS05CEo5AaNsQCbnoSjdGz9OelJuVuC-mUDQR5GLcwxV";

        let layer = new MapImageLayer({
            url: "https://nowcoast.noaa.gov/arcgis/rest/services/nowcoast/radar_meteo_imagery_nexrad_time/MapServer/"
        });

        const map = new Map({
            basemap: "arcgis-streets", // Basemap layer service
        });

        map.add(layer);

        const view = new MapView({
            map: map,
            center: [longitude, latitude], // Longitude, latitude
            zoom: 6, // Zoom level
            container: "map"
        });


        const graphicsLayer = new GraphicsLayer();
        map.add(graphicsLayer);

        const point = { //Create a point
            type: "point",
            longitude: longitude,
            latitude: latitude
        };

        const simpleMarkerSymbol = {
            type: "simple-marker",
            outline: {
                color: "red",
                width: 4
            },
            size: 10,
            style: "x"
        };

        const pointGraphic = new Graphic({
            geometry: point,
            symbol: simpleMarkerSymbol
        });

        graphicsLayer.add(pointGraphic);

        const legend = new Legend({
            view: view
        });
        const legendExpand = new Expand({
            expandIconClass: "esri-icon-legend",
            expandTooltip: "Legend",
            view: view,
            content: legend,
            expanded: false
        });
        view.ui.add(legendExpand, "top-left");

/*        const locate = new Locate({
            view: view,
            useHeadingEnabled: false,
            goToOverride: function(view, options) {
                options.target.scale = 1500;
                return view.goTo(options.target);
            }
        });
        view.ui.add(locate, "top-left");*/

    });
}
