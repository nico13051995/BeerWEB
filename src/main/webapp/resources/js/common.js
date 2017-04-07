
var map;
var infoW;
var myPos;
var salePoints = [];

function getPoints(coords) {
    //console.log(coords);

    if (coords !== 'undefined') {
        coords = coords.lat + ',' + coords.lng;
    }
    else {
        coords = null;
    }

    $.ajax({
        url: 'api/points/' + coords,
        method: 'POST',
        success: function (response) {
            //console.log(JSON.stringify( response ));
            var jsonData = JSON.parse(JSON.stringify( response ));

            var markers = new Array();
            for (var i = 0; i < jsonData.salePoints.length; i++) {
                var point = jsonData.salePoints[i];
                markers[i] = addMarker(point);
                addInfoWindow(markers[i], point);
            }

            myPos = {lat: jsonData.userLocation.lat, lng: jsonData.userLocation.lng};
            // map.setCenter(new google.maps.LatLng(jsonData.userLocation.lat, jsonData.userLocation.lng));
            // infoW.setContent(markers[0].getTitle());
            // infoW.open(map, markers[0]);
            $.notify(jsonData.userIp, "info");
        }
    });
}

function addMarker(point) {
    var m = new google.maps.Marker({
        position: {lat: point.lat, lng: point.lng},
        map: map,
        title: point.name
    });
    return m;
}

function addInfoWindow(marker, point) {
    marker.addListener('click', function () {
        // alert(point.name);
        $('#info-logo').attr('src', point.logo);
        $('#info-text').text(point.city + ', ' + point.street + ' №' + point.building);
        infoW.open(map, marker);
    });
}

function openTab(evt, tabName) {
    // Declare all variables
    var i, tabcontent, tablinks;

    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tab-container");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("tab");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    // Show the current tab, and add an "active" class to the button that opened the tab
    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";
}

function handleAndroidError() {
    map.setCenter(new google.maps.LatLng(myPos.lat, myPos.lng));

}