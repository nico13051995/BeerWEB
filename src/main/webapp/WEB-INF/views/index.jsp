<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Geolocation</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">

    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">

</head>
<body>
<div id="wrapper">
    <div class="tabs">
        <button class="tab" type="button" onclick="openTab(event, 'map')">Map</button>
        <button class="tab active" type="button" onclick="openTab(event, 'products')">List</button>
    </div>
</div>
<div id="map" class="tab-container"></div>
<div id="products" class="tab-container"  style="display: none"></div>

<script src="<c:url value="/resources/js/jquery.min.js " />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js " />"></script>
<script src="<c:url value="/resources/js/notify.js" />"></script>
<script src="<c:url value="/resources/js/common.js " />"></script>
<script>
    function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
            center: {lat: -34.397, lng: 150.644},
            zoom: 12,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        });

        var content = '<div id="info"><img id="info-logo" src="<c:url value="/resources/logo.png"/>"><p id="info-text">місто, вулиця, номер будинку</p></div>';
        infoW = new google.maps.InfoWindow({
            content: content
        });
    }

    // Try HTML5 geolocation.
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            var pos = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
//            console.log(pos);
            getPoints(pos);

            var im = 'http://www.robotwoods.com/dev/misc/bluecircle.png';
            var userMarker = new google.maps.Marker({
                position: pos,
                map: map,
                icon: im
            });

            map.setCenter(pos);
        }, function () {
            handleAndroidError();
//                    handleLocationError(true, infoWindow, map.getCenter());
        }, {
            enableHighAccuracy: true,
            timeout: 5000
        });
    } else { getPoints(undefined); }

</script>
<script type="text/javascript" async defer
        src="https://maps.googleapis.com/maps/api/js?v=3.26&key=AIzaSyAUx8OFmTxpyDvCS-r4RQPAX6BLQDXKd8o&callback=initMap">
</script>
</body>
</html>