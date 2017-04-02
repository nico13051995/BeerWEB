<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Yandex</title>

    <script src="https://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="<c:url value="/resources/js/notify.js" />"></script>

</head>
<body>
<div id="map" style="width: 600px; height: 400px"></div>
<p id="data"></p>
<script type="text/javascript">
    ymaps.ready(init);
    var myMap;

    function init(){
        myMap = new ymaps.Map("map", {
            center: [55.76, 37.64],
            zoom: 7
        });

        ymaps.geolocation.get({
            // Выставляем опцию для определения положения по ip
            provider: 'auto',
            // Автоматически геокодируем полученный результат.
            autoReverseGeocode: true
        }).then(function (result) {
            // Выведем в консоль данные, полученные в результате геокодирования объекта.
            var data = result.geoObjects.get(0).properties.get('metaDataProperty');
            console.log(data.GeocoderMetaData.text);
            $.notify(data.GeocoderMetaData.text, "info");
            $('#data').text(data.GeocoderMetaData.text);
        });
    }
</script>
</body>
</html>