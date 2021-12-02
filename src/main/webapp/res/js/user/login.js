var frm = document.querySelector('#frm');
if(frm) {
    function clkSubmitEvent(e) {
        if(frm.uid.value.length < 5 || frm.uid.value.length > 20) {
            alert('아이디를 확인해 주세요.');
            e.preventDefault();
            return;
        }
        if(frm.upw.value.length < 5) {
            alert('비밀번호를 확인해 주세요.');
            e.preventDefault();
            return;
        }
    }
    frm.addEventListener('submit', clkSubmitEvent);

    var btn = document.querySelector('#btn');
    if(btn) {
        btn.addEventListener('click', function (e) {
            if(frm.upw.type === 'password') {
                btn.value = '비밀번호 감추기';
                frm.upw.type = 'text';
            } else {
                btn.value = '비밀번호 보이기';
                frm.upw.type = 'password';
            }
        });
    }
}


