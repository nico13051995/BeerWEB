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

<script async defer
        src="https://maps.googleapis.com/maps/api/js?v=3&key=AIzaSyAUx8OFmTxpyDvCS-r4RQPAX6BLQDXKd8o&callback=initMap">
</script>

<script src="<c:url value="/resources/js/jquery.min.js " />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js " />"></script>
<script src="<c:url value="/resources/js/common.js " />"></script>
</body>
</html>