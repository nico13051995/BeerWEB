<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Page</title>
    <c:set var="contextPath" scope="request" value="${pageContext.request.contextPath}"/>

    <link rel="stylesheet" href="${contextPath}resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${contextPath}resources/css/font-awesome.min.css">
    <link rel="stylesheet" href="${contextPath}resources/css/adm-style.css">
</head>
<body>

<div id="wrapper">

    <div class="adm-left-sidebar">
        <a href="${pageContext.request.contextPath}/">Адмін панель</a>

        <button onclick="openAdmTab('item')" class="adm-tab">Точки продаж</button>
        <button onclick="openAdmTab('event')" class="adm-tab">Продукція</button>
        <%--<button onclick="openAdmTab('promotions')" class="adm-tab">Joins</button>--%>
        <button onclick="openAdmTab('users')" class="adm-tab">Загрузки</button>
    </div>

    <div id="adm-containers">
        <div id="item" class="adm-container">
            <h2>Точки продажу</h2>
            <table class="users_table">
                <thead>
                <tr>
                    <th>Назва точки</th>
                    <th>Адрес</th>
                    <th>Кількість продукції</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${points}" var="p">
                    <tr sp-id="${p.id}">
                        <td>${p.name}</td>
                        <td>${p.address}</td>
                        <td>${p.joins.size()}</td>
                        <td>
                            <button class="b-control" onclick="editSP('${p.id}');"><i class="fa fa-pencil-square fa-2x"></i></button>
                            <button class="b-control" onclick="deleteSp('${p.id}');"><i class="fa fa-minus-circle  fa-2x"></i></button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div id="sp-edit">
                <h4>Точка продажу:</h4>
                <div class="sp-edit-content">
                    <input id="sp-id" type="hidden"/>
                    <input id="sp-name" type="text" placeholder="name"/>
                    <input id="sp-adr" type="text" placeholder="address"/>

                    <button onclick="sendEditedSP()">Edit</button>
                </div>

                <button onclick="$('#sp-edit').hide();" id="sp-edit-exit">
                    <i class="fa fa-times fa-2x fa-fw"></i>
                </button>
            </div>
        </div>
        <div id="event" class="adm-container">
            <h2>Продукція</h2>
            <table class="users_table">
                <thead>
                <tr>
                    <th>Назва</th>
                    <th>Опис</th>
                    <th>Логотип</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${beers}" var="b">
                    <tr>
                        <td>${b.name}</td>
                        <td>${b.description}</td>
                        <td><img class="logo" src="${b.logo}"></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div id="users" class="adm-container" style="display: block">
            <div class="container">
                <div class="row">
                    <div class="col-md-5">
                        <h2>Точки</h2>
                        <form class="upload-form" action="${pageContext.request.contextPath}/uploadPoints" method="post"
                              enctype="multipart/form-data" accept-charset="UTF-8">
                            <div class="form-group">
                                <label id="filelabelPoint" for="filePoint" class="btn btn-default btn-file">
                                    Вибрати файл&hellip;
                                </label>
                                <input id="filePoint" required type="file" name="file" class="btn btn-upload"
                                       style="display: none"/>

                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-success btn-upload"><i class="fa fa-upload"
                                                                                            aria-hidden="true">
                                    Завантажити</i>
                                </button>
                            </div>

                            <c:if test="${not empty answerPoint}">
                                <div id="points" class="result-list">
                                    <div class="new">
                                        <h3>Нові точки:</h3>
                                        <ul class="result-new">
                                            <c:forEach items="${answerPoint.newPoints}" var="np">
                                                <li>${np.name}</li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                    <div class="updated">
                                        <h3>Обновленні точки:</h3>
                                        <ul class="result-updated">
                                            <c:forEach items="${answerPoint.updatedPoints}" var="up">
                                                <li>${up.name}</li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </c:if>

                        </form>
                    </div>
                    <div class="col-md-5">
                        <h2>Продукція</h2>
                        <form class="upload-form" action="${pageContext.request.contextPath}/uploadBeers" method="post"
                              enctype="multipart/form-data" accept-charset="UTF-8">
                            <div class="form-group">
                                <label id="filelabelBeer" for="fileBeer" class="btn btn-default btn-file">
                                    Вибрати файл&hellip;
                                </label>
                                <input id="fileBeer" required type="file" name="file" class="btn btn-upload"
                                       style="display: none"/>

                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-success btn-upload"><i class="fa fa-upload"
                                                                                            aria-hidden="true">
                                    Завантажити</i>
                                </button>
                            </div>

                            <c:if test="${not empty answerBeer}">
                                <div id="points" class="result-list">
                                    <div class="new">
                                        <h3>Нові товари:</h3>
                                        <ul class="result-new">
                                            <c:forEach items="${answerBeer.newBeers}" var="np">
                                                <li>${np.name}</li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                    <div class="updated">
                                        <h3>Обновленні товари:</h3>
                                        <ul class="result-updated">
                                            <c:forEach items="${answerBeer.updatedBeers}" var="up">
                                                <li>${up.name}</li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </c:if>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${contextPath}resources/js/jquery.min.js"></script>
<script src="${contextPath}resources/js/bootstrap.min.js"></script>
<script src="${contextPath}resources/js/common.js"></script>
<script>
    $(document).ready(function () {
        $('#sp-edit').hide();

        $('#filePoint:file').change(function () {
            var fileName = $(this).val();
            fileName = fileName.split('\\');
            fileName = fileName[fileName.length - 1];
//            alert(fileName);
            console.log(fileName === '');
            if (fileName === '') fileName = 'Файл не вибрано';

            $('#filelabelPoint').text(fileName);
        });
        $('#fileBeer:file').change(function () {
            var fileName = $(this).val();
            fileName = fileName.split('\\');
            fileName = fileName[fileName.length - 1];
//            alert(fileName);
            console.log(fileName === '');
            if (fileName === '') fileName = 'Файл не вибрано';

            $('#filelabelBeer').text(fileName);
        });

    });

    function editSP(spId) {
        $('#sp-edit').show();

        var point = $('tr[sp-id="'+ spId +'"]');
//        console.log(point[0].children);

        var info = point[0].children;
//        console.log(info[0].outerText);

        $('#sp-id').val(spId);
        $('#sp-name').val(info[0].outerText);
        $('#sp-adr').val(info[1].outerText);
    }

    function sendEditedSP(spId) {
        var info = $('#sp-id').val() + '-' + $('#sp-name').val() + '-' + $('#sp-adr').val();

        $.ajax({
            url: 'changePoint/' + info,
            method: 'PUT'
//            success: function (response) {
////                window.location.reload();
//                var tds = $('tr[sp-id=\''+ $('#sp-id').val() +'\']').children();
//
//                tds[0].text($('#sp-name').val());
//                tds[1].text($('#sp-adr').val());
//            }
        }).done(function (data) {
            location.reload();
        });
    }

    function deleteSp(spId) {
        $.ajax({
            url: 'deletePoint/' + spId,
            method: 'DELETE'
        }).done(function (data) {
            location.reload();
        });
    }
</script>
</body>
</html>