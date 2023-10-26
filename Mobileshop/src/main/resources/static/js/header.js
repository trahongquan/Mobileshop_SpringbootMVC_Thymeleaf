/**
 * *******************************************
 * Số lượng hàng hiện có trong giỏi hàng
 * *******************************************
 */
$(document).ready( function () {
    var cookies = document.cookie;

    var cartCookie = getCookie("cartCookie");
    // Chuyển cartCookie thành Object updatedCart (nếu có)
    var updatedCart = cartCookie ? JSON.parse(atob(cartCookie)) : [];
    var cartqty = parseInt(updatedCart.phoneCartDTOSList.length)
    $('.cart-qty').text('(' + cartqty + ')');

    var size = cookies.length;


    // console.log(typeof size);
    // console.log(size);})
// Hàm lấy giá trị cookie bằng tên
    function getCookie(name) {
        var cookieValue = document.cookie.match('(^|;)\\s*' + name + '\\s*=\\s*([^;]+)');
        return cookieValue ? cookieValue.pop() : null;
    }
});

/**
 * *******************************************
 * Chuyển về dạng slice cho màn hình bé
 * *******************************************
 */
$(document).ready(function() {
    $('#toggle').click(function() {
        $('nav').slideToggle();
    });
})