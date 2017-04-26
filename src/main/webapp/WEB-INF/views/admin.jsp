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
    <div class="adm-head">
        <a href="${pageContext.request.contextPath}/">Адмін панель</a>

    </div>
    <div class="adm-left-sidebar">
        <button onclick="openAdmTab('item')" class="adm-tab">Точки продаж</button>
        <button onclick="openAdmTab('event')" class="adm-tab">Продукція</button>
        <%--<button onclick="openAdmTab('promotions')" class="adm-tab">Joins</button>--%>
        <button onclick="openAdmTab('users')" class="adm-tab">Загрузки</button>
    </div>

    <div id="adm-containers">
        <div id="item" class="adm-container">
            <h2>Точки продажу</h2>
            <table class="users_table">
                <tbody>
                <tr>
                    <th>Назва точки</th>
                    <th>Адрес</th>
                    <th>Кількість продукції</th>
                </tr>

                <c:forEach items="${points}" var="p">
                    <tr>
                        <td>${p.name}</td>
                        <td>${p.address}</td>
                        <td>${p.joins.size()}</td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
        <div id="event" class="adm-container">
            <h2>Продукція</h2>
            <table class="users_table">
                <tbody>
                <tr>
                    <th>Назва</th>
                    <th>Опис</th>
                    <th>Логотип</th>
                </tr>
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
        <%--<div id="promotions" class="adm-container">--%>
            <%--<button class="btn btn-upload">Upload sheets</button>--%>
            <%--<h2>Joins</h2>--%>
            <%--<table class="users_table">--%>
                <%--<tbody>--%>
                <%--<tr>--%>
                    <%--<th>Point Name</th>--%>
                    <%--<th>Beer Name</th>--%>
                    <%--<th>g33</th>--%>
                    <%--<th>g05</th>--%>
                    <%--<th>p1</th>--%>
                    <%--<th>p1.5</th>--%>
                    <%--<th>p2</th>--%>
                    <%--<th>k30</th>--%>
                    <%--<th>k50</th>--%>
                <%--</tr>--%>
                <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<td></td>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<td><input onclick="" type="checkbox"&ndash;%&gt;--%>
                <%--&lt;%&ndash;checked/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
                <%--</tbody>--%>
            <%--</table>--%>
        <%--</div>--%>
        <div id="users" class="adm-container" style="display: block">
            <div class="container">
                <div class="row">
                    <div class="col-md-5">
                        <h2>Точки</h2>
                        <form class="upload-form" action="${pageContext.request.contextPath}/uploadFIle" method="post"
                              enctype="multipart/form-data" accept-charset="UTF-8">
                            <div class="form-group">
                                <label id="filelabel" for="file" class="btn btn-default btn-file">
                                    Вибрати файл&hellip;
                                </label>
                                <input id="file" required type="file" name="file" class="btn btn-upload"
                                       style="display: none"/>

                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-success btn-upload"><i class="fa fa-upload"
                                                                                            aria-hidden="true">
                                    Завантажити</i>
                                </button>
                            </div>

                            <c:if test="${not empty answer}">
                                <div id="points" class="result-list">
                                    <div class="new">
                                        <h3>Нові точки:</h3>
                                        <ul class="result-new">
                                            <c:forEach items="${answer.newPoints}" var="np">
                                                <li>${np.name}</li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                    <div class="updated">
                                        <h3>Обновленні точки:</h3>
                                        <ul class="result-updated">
                                            <c:forEach items="${answer.updatedPoints}" var="up">
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
                        <%--<form class="upload-form" action="${pageContext.request.contextPath}/uploadFIle" method="post" enctype="multipart/form-data" accept-charset="UTF-8">--%>
                        <%--<button type="submit" class="btn btn-success btn-upload"><i class="fa fa-upload" aria-hidden="true"></i></button>--%>
                        <%--<input required type="file" name="file" class="btn btn-upload" placeholder="Upload sheets"/>--%>
                        <%--</form>--%>
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
        $('#file:file').change(function () {
            var fileName = $(this).val();
            fileName = fileName.split('\\');
            fileName = fileName[fileName.length - 1];
//            alert(fileName);
            console.log(fileName === '');
            if (fileName === '') fileName = 'Файл не вибрано';

            $('#filelabel').text(fileName);
        });
    });
</script>
</html>