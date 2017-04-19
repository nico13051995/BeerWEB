var map;
var myPos = 'wait for response';
var salePoints = [];
var windows = [];

var curPoint;

function getPoints(coords) {
    try {
        coords = coords.lat + ',' + coords.lng;
    } catch (e) {
        coords = null;
    }

    $.ajax({
        url: 'api/points/' + coords,
        method: 'GET',
        success: function (response) {
            //console.log(JSON.stringify( response ));
            var jsonData = JSON.parse(JSON.stringify(response));

            salePoints = jsonData.salePoints;
            //console.log(salePoints);

            for (var i = 0; i < salePoints.length; i++) {
                var point = salePoints[i];

                var marker = new google.maps.Marker({
                    position: {lat: point.lat, lng: point.lng},
                    map: map,
                    title: point.name
                });

                // markers[i] = marker;

                addInfoWindow(marker, point);
            }

            myPos = {lat: jsonData.userLocation.lat, lng: jsonData.userLocation.lng};
            // alert(myPos);
            if (coords === null) {
                handleAndroidError();
            }

            createListForPoints();
        }
    });
}

function addMarker(point) {
    return new google.maps.Marker({
        position: {lat: point.lat, lng: point.lng},
        map: map,
        title: point.name
    });
}

function addInfoWindow(marker, point) {
    var adr = point.city + ', ' + point.street + ' №' + point.building;
    var info = point.name + ', ' + Math.round(point.distance * 100) / 100 + 'км';

    var content = '<a id="info-link" href="info/' + point.id + '"><div id="info"><img id="info-logo" src="/resources/logo.png"><p id="info-text-head">' + info + '</p><p id = info-text-body>' + adr + '</p></div></a>';

    var infoW = new google.maps.InfoWindow({
        content: content
    });

    // windows.push(infoW);

    marker.addListener('click', function () {
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
    // console.log(myPos);
    map.setCenter(new google.maps.LatLng(myPos.lat, myPos.lng));
}

function createListForPoints() {
    salePoints.sort(function (a, b) {
        if (a.distance > b.distance) {
            return 1;
        }
        if (a.distance < b.distance) {
            return -1;
        }
        // a должно быть равным b
        return 0;
    });

    for (var i = 0; i < salePoints.length; i++) {
        $('#products').append(createPoint(salePoints[i]));
    }
}

function createPoint(point) {
    //console.log(point);
    var adr = point.city + ', ' + point.street + ' №' + point.building;
    var info = point.name + '<br>' + Math.round(point.distance * 100) / 100;
    var elem = $('<div onclick="window.open(\'info/' + point.id + '\');" class="point"><img class="p-logo"><p class="p-address">' + adr + '</p><span class="p-info">' + info + ' км</span><span class="p-arrow"> > </span></div></a>');

    return elem;
}

function openAdmTab(admTab) {
    var i;
    var x = document.getElementsByClassName("adm-container");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    document.getElementById(admTab).style.display = "block";
}

function my_road(p, u) {
    console.log(u);
    // var adr = curPoint.city + ', ' + curPoint.street + ' №' + curPoint.building;

    var src = 'https://www.google.com/maps/embed/v1/directions?key=AIzaSyAUx8OFmTxpyDvCS-r4RQPAX6BLQDXKd8o&origin='+ u +'&destination='+ p +'&avoid=tolls|highways';
    $('#map-frame').attr("src", src);
}