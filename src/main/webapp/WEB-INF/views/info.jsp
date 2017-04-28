<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Point</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="UTF-8">
    <c:url value="/" var="root"/>

    <link rel="stylesheet" href="${root}resources/css/font-awesome.min.css">
    <link rel="stylesheet" href="${root}resources/css/info-style.css">
</head>
<body>
<div id="info-header">
    <a href="${pageContext.request.contextPath}/">назад</a>
    <span>${point.address}, ${point.name}</span>
</div>
<div id="info-wrapper">
    <div id="img-wrapper">
        <iframe id="map-frame"
                frameborder="0" style="border:0"
                src="https://www.google.com/maps/embed/v1/place?key=AIzaSyAUx8OFmTxpyDvCS-r4RQPAX6BLQDXKd8o
    &q=Space+Needle,Seattle+WA" allowfullscreen>
        </iframe>
    </div>
    <div id="product-wrapper">
        <div id="product-wrapper-header">
            <span>Товари:</span>
            <i class="fa fa-beer p-icon"></i>
        </div>

        <div id="beers-wrapper">
            <c:forEach items="${point.joins}" var="join">
                <div class="beer">
                    <img class="b-logo" src="${join.beer.logo}" alt="${join.beer.name}">
                    <p class="b-name">${join.beer.name}</p>
                    <p class="b-info">${join.beer.description}</p>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<div id="info-footer">

        <a class="info-btn" href="tel:+380960292929"><span class="info-icon i-phone"></span><span>Подзвонити</span></a>
        <a id="a-map"  class="info-btn" ><span class="info-icon i-map"></span><span>Доїхати</span></a>
        <a class="info-btn" href="mailto:info@umanpivo.ua"><span class="info-icon i-mail"></span><span>Пошта</span></a>
        <a class="info-btn" href="http://umanpivo.ua/"><span class="info-icon i-site"></span><span>Сайт</span></a>

</div>

<script src="${root}resources/js/jquery.min.js"></script>
<script src="${root}resources/js/common.js"></script>
<script>
    var adr = '${point.address}'.replace(/\s+/g, '+');
    var up = myPos.replace(/\s+/g, '+');
    var src = 'https://www.google.com/maps/embed/v1/place?key=AIzaSyAUx8OFmTxpyDvCS-r4RQPAX6BLQDXKd8o&q='+ adr +'';
    $('#map-frame').attr("src", src);

    my_road('${point.address}', '${userLocation}');
</script>
</body>
</html>