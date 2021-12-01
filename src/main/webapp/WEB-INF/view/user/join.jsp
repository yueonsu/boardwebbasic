<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--서버에서 받아올 때는 name을 받아옴, id는 못 받음--%>
<%--<script defer></script>   자바스크립트를 가장 마지막에 실행시킴   --%>
<div>
    <form action="/user/join" method="post" id="frm" onsubmit="return joinChk()">
        <div><input type="text" name="uid" placeholder="id" required></div>
        <div><input type="password" name="upw" placeholder="password" required></div>
        <div><input type="password" id="reupw" placeholder="password comfirm" required></div>
        <div><input type="text" name="nm" placeholder="name" required></div>
        <div>
            <label>female<input type="radio" value="2" name="gender" checked></label>
            <label>male<input type="radio" value="1" name="gender"></label>
        </div>
        <div>
            <input type="submit" value="join">
            <input type="reset" value="reset">
        </div>
    </form>
</div>
<script src="/res/js/user/join.js"></script>