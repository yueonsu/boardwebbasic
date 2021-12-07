var reset = document.querySelector('#reset');
var frm = document.querySelector('#frm');
if(reset) {
    function resetBtn() {
        frm.title.value = '';
        frm.ctnt.value = '';
    }
}