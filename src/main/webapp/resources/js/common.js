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

            var icon = {
                url: 'https://www.sakai-ikimono.jp/design/img/map/pin-drag.png', // url
                scaledSize: new google.maps.Size(32, 48), // scaled size
                origin: new google.maps.Point(0, 0), // origin
                anchor: new google.maps.Point(0, 0) // anchor
            };

            var userMarker = new google.maps.Marker({
                position: myPos,
                map: map,
                icon: icon,
                animation: google.maps.Animation.BOUNCE
            });

            // alert(userMarker.position);

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
    var adr = point.address;
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
    // alert('Ip pos: ' + myPos.lat +' - '+ myPos.lng);
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
        return 0;
    });

    for (var i = 0; i < salePoints.length; i++) {
        var point = createPoint(salePoints[i]);
        $('#products').append(point);
        if (i % 2 === 0) {
            point.find('.point').css('background-color', '#b87371');
        }
    }
}

function createPoint(point) {
    //console.log(point);
    var adr = point.address;
    // adr = adr.split(',');
    // var lastItem = adr[adr.length - 1];
    // adr[adr.length - 1] = ' ' + adr[0];
    // lastItem = lastItem.trim();
    //
    // adr[0] = lastItem;


    var info = point.name + '<br>' + Math.round(point.distance * 100) / 100;
    var elem = $('<a href="info/' + point.id + '"><div class="point"><img class="p-logo"><p class="p-address">' + adr + '</p><span class="p-info">' + info + ' км</span><span class="p-arrow"> > </span></div></a>');

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
    var adr = p.replace(/\s+/g, '+');
    var up = u.replace(/\s+/g, '+');
    var url = 'https://www.google.com/maps/dir/' + up + '/' + adr + '';

    $('#a-map').attr("href", url);
}