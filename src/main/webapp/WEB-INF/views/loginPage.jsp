<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Digger</title>

    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/login-style.css"/>">
</head>
<body>
<div id="login-wrapper" class="tab-wrap">
    <div>
        <%--<ul class="nav nav-tabs" id="myTabs" role="tablist">--%>
            <%--<li role="presentation" class="active">--%>
                <%--<a href="#home" id="home-tab" role="tab" data-toggle="tab" aria-controls="home" aria-expanded="true">--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--&lt;%&ndash;<li role="presentation">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<a href="#profile" role="tab" id="profile-tab" data-toggle="tab" aria-controls="profile">&ndash;%&gt;--%>
                <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</li>&ndash;%&gt;--%>
        <%--</ul>--%>
        <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade in active" role="tabpanel" id="home" aria-labelledby="home-tab">
                <%--<div class="form-group">--%>
                    <%--Увійти через:--%>
                    <%--<ul class="social-icon">--%>
                        <%--<li>--%>
                            <%--<a href="/vkAuth"><img src="" alt="vk"></a>--%>
                        <%--</li>--%>
                        <%--&lt;%&ndash;<li>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<a href="#"><img src="" alt="fb"></a>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</li>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<li>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<a href="#"><img src="" alt="ok"></a>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</li>&ndash;%&gt;--%>
                    <%--</ul>--%>
                <%--</div>--%>

                <form action="${pageContext.request.contextPath}/login" method="post" class="form-box">
                    <div class="form-group">
                        <label for="inputMail_l" class="control-label">Логін:</label>
                        <input type="text" class="form-control" id="inputMail_l" name="user_login">
                    </div>
                    <div class="form-group">
                        <label for="inputPassword_l" class="control-label">Пароль:</label>
                        <input type="password" class="form-control" id="inputPassword_l" name="user_password">
                    </div>
                    <div class="form-check">
                        <label class="form-check-label">
                            <input name="remember-me" type="checkbox" class="form-check-input">
                            Запам'ятати?
                        </label>
                    </div>
                    <button type="submit" class="btn btn-form">Увійти</button>
                </form>

            </div>
            <%--<div class="tab-pane fade" role="tabpanel" id="profile" aria-labelledby="profile-tab">--%>
                <%--<form:form action="${pageContext.request.contextPath}/register" modelAttribute="user" method="post" class="form-box">--%>
                    <%--<div class="form-group">--%>
                        <%--<label for="inputMail" class="control-label"><spring:message code="mail"/></label>--%>
                        <%--<input type="email" class="form-control" id="inputMail" name="username" required>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label for="inputPassword" class="control-label"><spring:message code="pass"/></label>--%>
                        <%--<input type="password" onkeyup="checkPass()" class="form-control" id="inputPassword" name="password" required>--%>
                        <%--<span id="passStr"></span>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label for="inputPassword_again" class="control-label"><spring:message code="passC"/></label>--%>
                        <%--<input type="password" class="form-control" id="inputPassword_again" name="passwordConfirm" required>--%>
                    <%--</div>--%>


                    <%--<button type="submit" class="btn btn-form"><spring:message code="sign_up"/></button>--%>
                <%--</form:form>--%>
            <%--</div>--%>
        </div>
    </div>
</div>


<script src="<c:url value="/resources/js/jquery.min.js " />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js " />"></script>
<script src="<c:url value="/resources/js/notify.js" />"></script>
<script src="<c:url value="/resources/js/common.js " />"></script>
<script>
    <c:forEach items="${msg}" var="e">
        $.notify("${e.key}", "${e.value}");
    </c:forEach>
</script>
</body>
</html>