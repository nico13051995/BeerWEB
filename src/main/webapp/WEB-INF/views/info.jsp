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
    <span>${point.city}, ${point.name}</span>
</div>
<div id="info-wrapper">
    <div id="img-wrapper"></div>
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
    <button type="button" onclick="onclick=window.open('tel:${point.phone}');" class="info-btn"><span class="info-icon i-phone"></span>Подзвонити
    </button>
    <button type="button" onclick="test();" class="info-btn"><span class="info-icon i-map"></span><span>Доїхати</span></button>
    <button type="button" onclick="onclick=window.open('mailto:${point.mail}');" class="info-btn"><span
            class="info-icon i-mail"></span><span>Пошта</span></button>
    <button type="button" onclick="window.open('${point.site}');" class="info-btn"><span class="info-icon i-site"></span><span>Сайт</span></button>
</div>

<script src="${root}resources/js/jquery.min.js"></script>
<script>
    var adr = '${point.city}+${point.street}+${point.building}';
    adr = adr.replace(/\s+/g, '+');
    var size = $('#img-wrapper').width() * 2 + 'x' + $('#img-wrapper').height() * 2;
//    console.log(size);
    var url = 'https://maps.googleapis.com/maps/api/staticmap?autoscale=2&size='+ size +'&zoom=17&maptype=roadmap&key=AIzaSyAUx8OFmTxpyDvCS-r4RQPAX6BLQDXKd8o&format=png&visual_refresh=true&markers=size:mid%7Ccolor:0xff0000%7Clabel:%7C'+adr+'';
    $('#img-wrapper').css({"background": "url(" + url + ") no-repeat center"});
</script>
</body>
</html>