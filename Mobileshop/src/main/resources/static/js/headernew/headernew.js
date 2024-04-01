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
    var cartqty = parseInt(updatedCart.listProductsIDandQuantity.length)
    $('#mini-cart-count').text('(' + cartqty + ')');
    /*const totalAmount = updatedCart.listProductsIDandQuantity.reduce((total, item) => {
        const itemTotal = item.quantity * item.price;
    return total + itemTotal;
    }, 0);*/
    var total = 0;
    updatedCart.listProductsIDandQuantity.forEach(item => {
        total += item.quantity * item.price;
    console.log('total: '+total);

    })
    $('#mini-total-amount').text('$' + total);
    console.log(cartqty);
    console.log(total);
// Hàm lấy giá trị cookie bằng tên
    function getCookie(name) {
        var cookieValue = document.cookie.match('(^|;)\\s*' + name + '\\s*=\\s*([^;]+)');
        return cookieValue ? cookieValue.pop() : null;
    }
});