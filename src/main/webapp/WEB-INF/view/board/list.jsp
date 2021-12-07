<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <table id="boardTable">
        <thead>
            <tr>
                <th>no</th>
                <th>title</th>
                <th>hits</th>
                <th>writer</th>
                <th>reg-datetime</th>
            </tr>
        </thead>
        <c:forEach items="${requestScope.list}" var="item">
            <tbody>
                <tr class="record" onclick="moveToDetail(${item.iboard});">
                    <td><c:out value="${item.iboard}"></c:out></td>
                    <td><c:out value="${item.title}"></c:out></td>
                    <td><c:out value="${item.hit}"></c:out></td>
                    <td><c:out value="${item.writerNm}"></c:out></td>
                    <td><c:out value="${item.rdt}"></c:out></td>
                </tr>
            </tbody>
        </c:forEach>
    </table>
</div>
<script src="/res/js/board/list.js"></script>