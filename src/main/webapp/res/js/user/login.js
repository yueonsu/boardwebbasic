var frm = document.querySelector('#frm');
if(frm) {
    function proc() {
        alert('전송');
    }

    var proc2 = function () {
        alert('전송!');
    }
    frm.addEventListener('submit', proc);
//     frm.addEventListener('submit', proc2);
//     frm.addEventListener('submit', function(e) { e->안 적어도 댐
//         alert('전송');
//         e.preventDefault(); --> submit의 기존 이벤트를 막음
//     });
}


