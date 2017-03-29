<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Geolocation</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">

    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">

</head>
<body>
<div id="wrapper">
    <h1 class="title">Sale Points</h1>
    <div class="tabs">
        <button class="tab" type="button">Map</button>
        <button class="tab" type="button">List</button>
    </div>
</div>
<div id="map"></div>

<script src="<c:url value="/resources/js/jquery.min.js " />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js " />"></script>
<script src="<c:url value="/resources/js/common.js " />"></script>
<script>
    function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
            center: {lat: -34.397, lng: 150.644},
            zoom: 12,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        });
        var infoWindow = new google.maps.InfoWindow({map: map});
        infoW = new google.maps.InfoWindow({
            content: "content"
        });

        // Try HTML5 geolocation.
        if (navigator.geolocation) {
            infoWindow.setContent("Error![t]");
            navigator.geolocation.getCurrentPosition(function (position) {
                var pos = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude
                };
                getPoints(pos);
                infoWindow.setPosition(pos);
                infoWindow.setContent('You here!');
                map.setCenter(pos);
            }, function () {
                handleLocationError(true, infoWindow, map.getCenter());
            });
        }
        else {
            infoWindow.setContent("Error![f]");
            // Browser doesn't support Geolocation
            handleLocationError(false, infoWindow, map.getCenter());
        }
    }

    function handleLocationError(browserHasGeolocation, infoWindow, pos) {
        infoWindow.setPosition(pos);
        infoWindow.setContent(browserHasGeolocation ?
                'Error: The Geolocation service failed.' :
                'Error: Your browser doesn\'t support geolocation.');
    }

</script>
<script type="text/javascript" async defer
        src="https://maps.googleapis.com/maps/api/js?v=3&key=AIzaSyAUx8OFmTxpyDvCS-r4RQPAX6BLQDXKd8o&callback=initMap">
</script>
</body>
</html>