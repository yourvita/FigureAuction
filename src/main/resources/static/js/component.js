document.addEventListener("DOMContentLoaded", () => {
    let phone = document.getElementById('inputPhone');
    if (phone) {
        phone.addEventListener("keyup", (e) => {
            console.log(e.key != 'Backspace');
            const phoneNo = $('#inputPhone').val();
            if (e.key != 'Backspace') {
                if (phoneNo.length == 3 || phoneNo.length == 8) {
                    $('#inputPhone').val(phoneNo + '-');
                }
            }
        });
    }
});