function joinChk() {
    var frm = document.querySelector('#frm');
    // 속성, 자식 접근할 때 "." 을 씀
    // 이름 아이디 속성으로 접근 가능
    if(frm.uid.value.length < 5 || frm.uid.value.length > 20) { // value => 속성
        alert('아이디를 5~20자 사이로 작성해 주세요.');
        return false;
    } else if(frm.upw.value.length < 5) {
        alert('비밀번호를 5자 이상 작성해 주세요.');
        return false;
    } else if(frm.upw.value !== frm.reupw.value) {
        alert('비밀번호를 확인해 주세요.');
        return false;
    } else if(frm.nm.value.length > 5) {
        alert('이름을 확인해 주세요.')
        return false;
    }
    return true;
}