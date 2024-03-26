/** ////////////////////////////////////////////////// */
/** ////////////////////////////////////////////////// */
/** ////////////////////////////////////////////////// */


/**
 * Tính Total Amount
 * */
{
    function calculateTotalAmount() {
        var total = 0;
        var checkedItems = $('.SelectItem:checked');
        checkedItems.each(function () {
            var row = $(this).closest('tr');
            var price = parseFloat(row.find('.total').text().replace("$", ""));
            total += price;
        });
        $('#totalAmount').text('$' + total.toFixed(2));
    }
}
/**
 * *******************************************
 * Cập nhật cột Total khi thay đổi số lượng:
 * *******************************************
 */
{
// Bắt sự kiện khi số lượng thay đổi
    $('.quantity').on("input", function () {
        var quantity = parseInt($(this).val());
        var price = parseFloat($(this).closest("tr").find("td .price").text().replace("$", ""));
        console.log(price);
        console.log(quantity);
        var total = (quantity * price).toFixed(2);
        $(this).closest("tr").find(".total").text("$" + total);
        console.log("total: "+ total);
        updateProductInCookie($(this));
    });

// Cập nhật thông tin sản phẩm trong cookie
    function updateProductInCookie(inputElement) {
        var productID = $(inputElement).closest("tr").data("product-id");
        var cartCookie = getCookie("cartCookie");
        var updatedCart = cartCookie ? JSON.parse(atob(cartCookie)) : [];
        for (var i = 0; i < updatedCart.listProductsIDandQuantity.length; i++) {
            if (updatedCart.listProductsIDandQuantity[i].id === productID) {
                /** Lấy giá trị quantity mới */
                var quantity = parseInt($(inputElement).val());
                /** Cập nhật quantity trong phoneCartDTO*/
                updatedCart.listProductsIDandQuantity[i].quantity = quantity;
                console.log("quantity: " + quantity)
                console.log("updatedCart quantity: " + updatedCart.listProductsIDandQuantity[i].quantity)
                break;
            }
        }
        updateCartCookie(updatedCart);
    }
    function getCookie(name) {
        var cookieValue = document.cookie.match('(^|;)\\s*' + name + '\\s*=\\s*([^;]+)');
        return cookieValue ? cookieValue.pop() : null;
    }

}
/**
 * *******************************************
 * Cập nhật Total Amount khi chọn sản phẩm:
 * *******************************************
 */
{
$('.SelectItem').click(function () {
    calculateTotalAmount();
    })
}
/**
 * *******************************************
 * Xóa hàng và cập nhật Total Amount:
 * *******************************************
 */
{
// Bắt sự kiện khi nút Remove được click
    $(".remove").click(function () {
        var row = $(this).closest("tr");
        var productID = row.data("product-id");
        console.log('productID; '+productID)
        row.remove();
        calculateTotalAmount();
        removeProductFromCookie(productID);
    });

    function removeProductFromCookie(productID) {
        var cartCookie = getCookie("cartCookie");
        var updatedCart = cartCookie ? JSON.parse(atob(cartCookie)) : [];
        for (var i = 0; i < updatedCart.listProductsIDandQuantity.length; i++) {
            if (updatedCart.listProductsIDandQuantity[i].id === productID) {
                // dùng hàm splice Xóa phần tử i trong phoneCartDTOSList nằm trong đối tượng updatedCart
                updatedCart.listProductsIDandQuantity.splice(i, 1);
                break;
            }
        }
        for (var i = 0; i < updatedCart.ProductsID.length; i++) {
            if (updatedCart.ProductsID[i] === productID) {
                updatedCart.ProductsID.splice(i, 1);
                updatedCart.productsID.splice(i, 1);
                break;
            }
        }
        updateCartCookie(updatedCart);
    }
}
/**
 * *******************************************
 * bắt sự kiện khi thay đổi số lượng thì Total Amount cũng thay đổi nếu hàng đó được checked select item
 * *******************************************
 * */
{
// Sự kiện khi số lượng thay đổi
    $('.quantity').on('input', function () {
        var row = $(this).closest('tr');
        var isChecked = row.find('input[type=checkbox]').prop('checked');
        var quantity = parseInt($(this).val());
        var price = parseFloat(row.find('td .price').text().replace('$', ''));
        var total = (quantity * price).toFixed(2);
        row.find('.total').text('$' + total);
        var SelectAll = $('#SelectAll');
        if(SelectAll.prop('checked') || isChecked){
            calculateTotalAmount();
        }
    });
}

/**
 * *******************************************
 * // Cập nhật cookie với danh sách sản phẩm cập nhật
 * *******************************************
 * */
{// Cập nhật cookie với danh sách sản phẩm cập nhật
    function updateCartCookie(updatedCart) {
        var cartJson = JSON.stringify(updatedCart);
        var cartBase64 = btoa(cartJson);
        document.cookie = "cartCookie=" + cartBase64 + "; expires=Thu, 31 Dec 2099 23:59:59 UTC; ";
    }
}
/**
 * *******************************************
 * // Select All checkbox
 * *******************************************
 * */
{
    var selectAllCheckbox = document.getElementById("SelectAll");

// Lấy tất cả các checkbox bên dưới
    var checkboxes = document.querySelectorAll(".SelectItem");

// Thêm sự kiện click vào checkbox SelectAll
    selectAllCheckbox.addEventListener("click", function () {
        // Nếu checkbox SelectAll được chọn thì chọn tất cả các checkbox bên dưới
        var total = 0;
        if (selectAllCheckbox.checked) {
            for (var i = 0; i < checkboxes.length; i++) {
                checkboxes[i].checked = true;

                var row = checkboxes[i].closest('tr');
                var price = parseFloat($(row).find('td .price ').text().replace('$', ''));
                var quantity = parseInt($(row).find('.quantity').val());
                var totalRow = (quantity * price);
                // Cộng tổng giá trị của hàng hiện tại vào tổng giá trị
                total = total + parseFloat(totalRow);
                console.log(total.toFixed(2))
            }
        }
        // Nếu checkbox SelectAll không được chọn thì bỏ chọn tất cả các checkbox bên dưới
        else {
            for (var i = 0; i < checkboxes.length; i++) {
                checkboxes[i].checked = false;
                var totaltext = $('#totalAmount');
            }
        }
        $('#totalAmount').text('$' + total.toFixed(2).toString());
    });
}

/**
 * *******************************************
 * // CartSession API (Không hoạt động)
 * *******************************************
 * */
{
// $(document).ready(function () {
//     var DataFromSession = [[${cartSession}]];
//     var serializedData = JSON.stringify(DataFromSession); // Serialize the object to JSON
//     localStorage.setItem('cartSessionFromServer', serializedData);
//
//     var cartSessionFromServer = localStorage.getItem('cartSessionFromServer');
//     var parsedData = JSON.parse(cartSessionFromServer); // Deserialize the JSON
//     console.log('parsedData: =');
//     console.log(parsedData);
//     console.log('typeof parsedData: ' + typeof parsedData);
//     var data = JSON.stringify(parsedData);
//     console.log('JSON.stringify(parsedData): ' + JSON.stringify(parsedData));
//     $.ajax({
//         type: 'POST',
//         url: '/api/updateSessionData',
//         data: data, // Sử dụng JSON.stringify để chuyển đổi thành chuỗi JSON
//         contentType: 'application/json', // Đặt tiêu đề 'Content-Type' là 'application/json'
//         success: function(response) {
//             console.log('Dữ liệu đã được cập nhật trên máy chủ.');
//         },
//         error: function(error) {
//             console.error('Lỗi khi gửi dữ liệu đến máy chủ: ' + error);
//         }
//     });
// });
}