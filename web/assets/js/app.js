$(function () {
    $('.navbar li a').parent().removeClass('active');
    $('a[href="' + this.location.pathname + '"]').parent().addClass('active');
});