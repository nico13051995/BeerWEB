<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"%>
<!DOCTYPE html>
<html>
<head>
    <title>Point</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="UTF-8">
    <c:url value="/" var="root" />

    <link rel="stylesheet" href="${root}resources/css/info-style.css">
    <link rel="stylesheet" href="${root}resources/css/font-awesome.min.css">
</head>
<body>
<div id="info-header">
    <span>${point.city}, ${point.name}</span>
</div>
<div id="info-wrapper">
    <div id="img-wrapper"></div>
    <div id="product-wrapper">
        <c:forEach items="${point.joins}" var="join">
            <div>
                ${join.beerId}
            </div>
        </c:forEach>
    </div>
</div>
<div id="info-footer">
    <button type="button" onclick="test();" class="info-btn"><span class="info-icon"></span><span>Подзвонити</span></button>
    <button type="button" onclick="test();" class="info-btn"><span class="info-icon"></span><span>Доїхати</span></button>
    <button type="button" onclick="test();" class="info-btn"><span class="info-icon"></span><span>Пошта</span></button>
    <button type="button" onclick="test();" class="info-btn"><span class="info-icon"></span><span>Сайт</span></button>
</div>

<script src="${root}resources/js/jquery.min.js"></script>
<script>
    var url = 'https://maps.googleapis.com/maps/api/staticmap?autoscale=1&size=640x480&maptype=roadmap&key=AIzaSyAUx8OFmTxpyDvCS-r4RQPAX6BLQDXKd8o&format=png&visual_refresh=true&markers=size:mid%7Ccolor:0xff0000%7Clabel:%7CЛьвівб+Русових+11';
    $('#img-wrapper').css({"background": "url("+url+") no-repeat center"});
</script>
</body>
</html>