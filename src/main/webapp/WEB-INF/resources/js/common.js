// Note: This example requires that you consent to location sharing when
// prompted by your browser. If you see the error "The Geolocation service
// failed.", it means you probably did not give permission for the browser to
// locate you.
var map;
var infoW;


function getPoints(coords) {
    //coords = coords.lat + ',' + coords.lng;
    //alert(coords);
    $.ajax({
        url: 'api/points',
        method: 'GET',
        success: function (response) {
            var jsonData = JSON.parse(response);
            var markers = new Array();
            for (var i = 0; i < jsonData.length; i++) {
                var point = jsonData[i];
                markers[i] = addMarker(point);
                addInfoWindow(markers[i]);
            }
            // console.log(markers);
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

function addInfoWindow(marker) {
    marker.addListener('click', function () {
            infoW.setContent(marker.getTitle());
            infoW.open(map, marker);
        });
}