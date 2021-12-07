<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${requestScope.title}</title>
    <link rel="stylesheet" href="/res/css/common.css">
</head>
<body>
<div class="container">
    <div class="header">
        <ul class="topMenu">
            <li><a href="/board/list">게시판</a></li>
            <c:if test="${sessionScope.loginUser != null}">
                <li><a href="/board/regmod">글쓰기</a></li>
                <li><a href="/user/logout">로그아웃</a></li>
                <li>${sessionScope.loginUser.nm}(${sessionScope.loginUser.uid}) 님 반갑습니다.</li>
            </c:if>
            <c:if test="${sessionScope.loginUser == null}">
                <li><a href="/user/login">로그인</a></li>
                <li><a href="/user/join">회원가입</a></li>
            </c:if>

        </ul>
    </div>
    <div class="body">
        <jsp:include page="/WEB-INF/view/${requestScope.page}.jsp"></jsp:include>
    </div>
    <div class="footer">
        footer
    </div>
</div>
<c:if test="${requestScope.err != null}">
    <script>
        var body = document.querySelector('body');
        body.onload = function () {
            setTimeout(function () {
                alert('<c:out value="${requestScope.err}"/>');
            }, 300);
        };
    </script>
</c:if>
</body>
</html>