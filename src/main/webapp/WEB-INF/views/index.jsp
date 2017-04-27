<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Beer Map</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <c:set var="contextPath" scope="request" value="${pageContext.request.contextPath}" />

    <link rel="stylesheet" href="${contextPath}resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${contextPath}resources/css/font-awesome.min.css">
    <link rel="stylesheet" href="${contextPath}resources/css/style.css">
</head>
<body>
<div id="wrapper">
    <div class="tabs">
        <button class="tab active" type="button" onclick="openTab(event, 'map')">Карта</button>
        <button class="tab" type="button" onclick="openTab(event, 'products')">Список</button>
    </div>
</div>
<div id="map" class="tab-container"></div>
<div id="products" class="tab-container" style="display: none"></div>

<script src="${contextPath}resources/js/jquery.min.js"></script>
<script src="${contextPath}resources/js/bootstrap.min.js"></script>
<script src="${contextPath}resources/js/notify.js"></script>
<script src="${contextPath}resources/js/common.js"></script>
<script>
    function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
            center: {lat: -34.397, lng: 150.644},
            zoom: 12,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        });

        map.addListener("click", function() {
            console.log(windows);
            for (var i = 0; i < windows.length; i++ ) {
                windows[i].close();
            }
        });
    }

    // Try HTML5 geolocation.
    if (navigator.geolocation) {
        alert('try to find location');
        navigator.geolocation.getCurrentPosition(function (position) {

            var pos = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            alert('HTML5 geo - '+ pos);

//            console.log(pos);
            getPoints(pos);

            map.setCenter(pos);
        }, function () {
            alert('HTML5 doesn`t support, try over ip');

            getPoints(undefined);
//                    handleLocationError(true, infoWindow, map.getCenter());
        }, {
            enableHighAccuracy: true,
            timeout: 5000
        });
    }

</script>
<script type="text/javascript" async defer
        src="https://maps.googleapis.com/maps/api/js?v=3.26&key=AIzaSyAUx8OFmTxpyDvCS-r4RQPAX6BLQDXKd8o&callback=initMap">
</script>
</body>
</html>