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
        <a href="${pageContext.request.contextPath}/">Beer Map</a>

    </div>
    <div class="adm-left-sidebar">
        <button onclick="openAdmTab('item')" class="adm-tab">Sale Points</button>
        <button onclick="openAdmTab('event')" class="adm-tab">Beers</button>
        <button onclick="openAdmTab('promotions')" class="adm-tab">Joins</button>
        <button onclick="openAdmTab('users')" class="adm-tab">Options</button>
    </div>

    <div id="adm-containers">
        <div id="item" class="adm-container">
            <form class="upload-form" action="${pageContext.request.contextPath}/uploadFIle" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
                <button type="submit" class="btn btn-success btn-upload"><i class="fa fa-upload" aria-hidden="true"></i></button>
                <input required type="file" name="file" class="btn btn-upload" placeholder="Upload sheets"/>
            </form>

            <h2>Sale points</h2>
            <table id="users_table">
                <tbody>
                <tr>
                    <th>Name</th>
                    <th>Logo</th>
                    <th>Phone</th>
                    <th>Mail</th>
                    <th>Site</th>
                    <th>City</th>
                    <th>Street</th>
                    <th>Building</th>
                </tr>
                <%--<tr>--%>
                <%--<td></td>--%>
                <%--<td><input onclick="" type="checkbox"--%>
                <%--checked/>--%>
                <%--</td>--%>
                <%--</tr>--%>
                </tbody>
            </table>
        </div>
        <div id="event" class="adm-container">
            <button class="btn btn-upload">Upload sheets</button>
            <h2>Beers</h2>
            <table id="users_table">
                <tbody>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Logo</th>

                </tr>
                <%--<tr>--%>
                <%--<td></td>--%>
                <%--<td><input onclick="" type="checkbox"--%>
                <%--checked/>--%>
                <%--</td>--%>
                <%--</tr>--%>
                </tbody>
            </table>
        </div>
        <div id="promotions" class="adm-container">
            <button class="btn btn-upload">Upload sheets</button>
            <h2>Joins</h2>
            <table id="users_table">
                <tbody>
                <tr>
                    <th>Point Name</th>
                    <th>Beer Name</th>
                    <th>g33</th>
                    <th>g05</th>
                    <th>p1</th>
                    <th>p1.5</th>
                    <th>p2</th>
                    <th>k30</th>
                    <th>k50</th>
                </tr>
                <%--<tr>--%>
                <%--<td></td>--%>
                <%--<td><input onclick="" type="checkbox"--%>
                <%--checked/>--%>
                <%--</td>--%>
                <%--</tr>--%>
                </tbody>
            </table>
        </div>
        <div id="users" class="adm-container">

        </div>
    </div>
</div>

<script src="${contextPath}resources/js/jquery.min.js"></script>
<script src="${contextPath}resources/js/bootstrap.min.js"></script>
<script src="${contextPath}resources/js/notify.js"></script>
<script src="${contextPath}resources/js/common.js"></script>
</body>
</html>