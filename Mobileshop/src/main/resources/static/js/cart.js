/** ////////////////////////////////////////////////// */
/** ////////////////////////////////////////////////// */
/** ////////////////////////////////////////////////// */

/**
 * Tính Total Amount
 */
function calculateTotalAmount() {
    var total = 0;
    var checkedItems = $('.SelectItem');
    checkedItems.each(function() {
        var checkbox = $(this);
        if(checkbox.prop('checked')){
            var row = $(this).closest('tr');
            var price = parseFloat(row.find('.total').text().replace("$", ""));
            total += price;
        }
    });
    $('#totalAmount').text('$' + total.toFixed(2));
}

/**
 * Cập nhật cột Total khi thay đổi số lượng
 */
$('.quantity').on("input", function() {
    var quantity = parseInt($(this).val());
    var price = parseFloat($(this).closest("tr").find("td .price").text().replace("$", ""));
    var total = (quantity * price).toFixed(2);
    $(this).closest("tr").find(".total").text("$" + total);
    updateProductInCookie($(this));
});

/**
 * Cập nhật thông tin sản phẩm trong cookie
 */
function updateProductInCookie(inputElement) {
    var productID = $(inputElement).closest("tr").data("product-id");
    var cartCookie = getCookie("cartCookie");
    var updatedCart = cartCookie ? JSON.parse(atob(cartCookie)) : [];
    for (var i = 0; i < updatedCart.listProductsIDandQuantity.length; i++) {
        if (updatedCart.listProductsIDandQuantity[i].id === productID) {
            var quantity = parseInt($(inputElement).val());
            updatedCart.listProductsIDandQuantity[i].quantity = quantity;
            break;
        }
    }
    updateCartCookie(updatedCart);
}

/**
 * Cập nhật Total Amount khi chọn sản phẩm
 */
$('.SelectItem').change(function() {
    calculateTotalAmount();
});

/**
 * Xóa hàng và cập nhật Total Amount
 */
$(".remove").click(function() {
    var row = $(this).closest("tr");
    var productID = row.data("product-id");
    row.remove();
    calculateTotalAmount();
    removeProductFromCookie(productID);
});

function removeProductFromCookie(productID) {
    var cartCookie = getCookie("cartCookie");
    var updatedCart = cartCookie ? JSON.parse(atob(cartCookie)) : [];
    for (var i = 0; i < updatedCart.listProductsIDandQuantity.length; i++) {
        if (updatedCart.listProductsIDandQuantity[i].id === productID) {
            updatedCart.listProductsIDandQuantity.splice(i, 1);
            break;
        }
    }
    updateCartCookie(updatedCart);
}

/**
 * Bắt sự kiện khi thay đổi số lượng thì Total Amount cũng thay đổi nếu hàng đó được checked select item
 */
$('.quantity').on('input', function() {
    var row = $(this).closest('tr');
    var isChecked = row.find('input[type=checkbox]').prop('checked');
    var quantity = parseInt($(this).val());
    var price = parseFloat(row.find('td .price').text().replace('$', ''));
    var total = (quantity * price).toFixed(2);
    row.find('.total').text('$' + total);
    var selectAllCheckbox = $('#SelectAll');
    if (selectAllCheckbox.prop('checked') || isChecked) {
        calculateTotalAmount();
    }
});

/**
 * Cập nhật cookie với danh sách sản phẩm cập nhật
 */
function updateCartCookie(updatedCart) {
    var cartJson = JSON.stringify(updatedCart);
    var cartBase64 = btoa(cartJson);
    document.cookie = "cartCookie=" + cartBase64 + "; expires=Thu, 31 Dec 2099 23:59:59 UTC; ";
}

/**
 * Select All checkbox
 */
var checkboxes = $('.SelectItem');
var selectAllCheckbox = $('.SelectAll');
$('.SelectAll').change(function() {
    var total = 0;
    if (selectAllCheckbox.prop('checked')) {
        checkboxes.each(function () {
            var checkbox = $(this);
            checkbox.prop('checked',true);
            checkbox.click();
            var row = checkbox.closest('tr');
            var price = parseFloat(row.find('td .price').text().replace('$', ''));
            var quantity = parseInt(row.find('.quantity').val());
            var totalRow = (quantity * price);
            total = total + parseFloat(totalRow);
        })
    } else {

        checkboxes.each(function () {
            var checkbox = $(this);
            checkbox.prop('checked',false);
            checkbox.click();
        })
    }
    document.getElementById('totalAmount').textContent = '$' + total.toFixed(2).toString();
});

function getCookie(name) {
    var cookieValue = document.cookie.match('(^|;)\\r*\\s*' + name + '\\s*=\\s*([^;]+)');
    return cookieValue ? cookieValue.pop() : '';
}

/*
$(document).ready(function () {
    if($('.SelectAll').prop('checked')){
        console.log($('.SelectAll').prop('checked'))
        $('.SelectAll').prop('checked', false)
    }
    if($('.SelectItem').prop('checked')){
        console.log($('.SelectItem').prop('checked'))
        $('.SelectItem').prop('checked', false)
    }
})*/
