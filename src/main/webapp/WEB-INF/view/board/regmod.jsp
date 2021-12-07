<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <form action="/board/regmod" method="post" id="frm">
        <div><input type="hidden" name="iboard" value="${requestScope.data.iboard}"></div>
        <div><label>제목: <input id="title" type="text" name="title" value="${requestScope.data.title}"></label></div>
        <div><label>내용: <textarea id="ctnt" name="ctnt">${requestScope.data.ctnt}</textarea></label></div>
        <div>
            <input type="submit" value="${title}">
            <input type="button" id="reset" value="초기화" onclick="resetBtn()">
        </div>
    </form>
</div>
<script src="/res/js/board/regmod.js"></script>