<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <form action="/user/login" method="post" id="frm">
        <p class="err">${requestScope.err}</p>
        <div><input type="text" name="uid" placeholder="id"></div>
        <div><input type="password" name="upw" placeholder="password" ></div>
        <div>
            <input type="submit" value="login" onclick="clkSubmitEvent(e)">
            <input type="button" value="비밀번호 보이기" id="btn">
        </div>
    </form>
    <div>
        <a href="/user/join">join</a>
    </div>
</div>
<script src="/res/js/user/login.js"></script>