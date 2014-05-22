$(function () {
    $('.navbar li a').parent().removeClass('active');
    $('a[href="' + this.location.pathname + '"]').parent().addClass('active');
    if ($('#body').length > 0) {
        $('#body').on('keyup', function countChar(e) {
            var textarea = e.target;
            var len = textarea.value.length;
            if (len > 140) {
                textarea.value = textarea.value.substring(0, 140);
            } else {
                $('#word-counter').text(140 - len);
            }
        });
    }
});