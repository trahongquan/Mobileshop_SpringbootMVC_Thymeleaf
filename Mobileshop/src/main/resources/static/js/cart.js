/** ////////////////////////////////////////////////// */
/** ////////////////////////////////////////////////// */
/** ////////////////////////////////////////////////// */


/**
 * Tính Total Amount
 * */
{
    function calculateTotalAmount() {
        var total = 0;

        // Lặp qua các hàng đã chọn
        $('input[type=checkbox]:checked').each(function () {
            var row = $(this).closest('tr');
            var price = parseFloat(row.find('.total').text().replace("$", ""));
            total += price;
        });

        // Hiển thị tổng giá trị
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
        console.log(total);


        // Cập nhật thông tin sản phẩm trong cookie
        updateProductInCookie($(this));
    });

// Cập nhật thông tin sản phẩm trong cookie
    function updateProductInCookie(inputElement) {
        // Lấy thông tin sản phẩm từ th:data-phone và th:data-product-model
        var productModel = $(inputElement).closest("tr").data("product-model");

        // Lấy cartCookie từ cookie (nếu có)
        var cartCookie = getCookie("cartCookie");
        // Chuyển cartCookie thành Object updatedCart (nếu có)
        /**
         *  updatedCart là một Object chứa 2 Array
         * có thông tin giống hệt nhau về dữ liệu là phoneCartDTOSList và products
         *
         * */

        var updatedCart = cartCookie ? JSON.parse(atob(cartCookie)) : [];
        console.log(cartCookie);
        console.log(updatedCart);

        // Tìm và cập nhật số lượng cho đối tượng PhoneCartDTO trong cart
        for (var i = 0; i < updatedCart.phoneCartDTOSList.length; i++) {
            if (updatedCart.phoneCartDTOSList[i].model === productModel) {
                /** Lấy giá trị quantity mới */
                var quantity = parseInt($(inputElement).val());
                /*console.log("quantity: "+ quantity);
                console.log("updatedCart.phoneCartDTOSList[i].quantity: "+ updatedCart.phoneCartDTOSList[i].quantity);*/
                /** Cập nhật quantity trong phoneCartDTO*/
                updatedCart.phoneCartDTOSList[i].quantityorder = quantity;
                /*console.log("Sau update:");
                console.log("updatedCart.phoneCartDTOSList[i].quantity: "+ updatedCart.phoneCartDTOSList[i].quantity);*/
                break;
            }
        }
        // Tìm và cập nhật số lượng cho đối tượng products trong updatedCart
        for (var i = 0; i < updatedCart.products.length; i++) {
            if (updatedCart.products[i].model === productModel) {
                /** Lấy giá trị quantity mới */
                var quantity = parseInt($(inputElement).val());
                /*console.log("quantity: "+ quantity);
                console.log("updatedCart.products[i].quantity: "+ updatedCart.products[i].quantity);*/
                /** Cập nhật quantity trong products*/
                updatedCart.products[i].quantityorder = quantity;
                /*console.log("Sau update:");
                console.log("updatedCart.products[i].quantity: "+ updatedCart.products[i].quantity);*/
                break;
            }
        }
        updateCartCookie(updatedCart);
    }

// Hàm lấy giá trị cookie bằng tên
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
// Bắt sự kiện khi checkbox sản phẩm thay đổi
/*$('input[type=checkbox]').change(function () {
    calculateTotalAmount();
});*/
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
        var productModel = row.data("product-model");
        console.log('productModel; '+productModel)

        // Xóa hàng khỏi bảng
        row.remove();

        // Cập nhật tổng giá trị
        calculateTotalAmount();

        // Xóa sản phẩm khỏi cookie
        removeProductFromCookie(productModel);
    });

    function removeProductFromCookie(productModel) {

        var cartCookie = getCookie("cartCookie");
        var updatedCart = cartCookie ? JSON.parse(atob(cartCookie)) : [];
        console.log('updatedCart: ');
        console.log(updatedCart);
        console.log('updatedCart.phoneCartDTOSList: ');
        console.log(updatedCart.phoneCartDTOSList);
        console.log('updatedCart.products: ');
        console.log(updatedCart.products);
        for (var i = 0; i < updatedCart.phoneCartDTOSList.length; i++) {
            console.log('vòng ' + i + ' :' + 'updatedCart.phoneCartDTOSList[i].model: '+updatedCart.phoneCartDTOSList[i].model+ '; productModel: ' + productModel)
            if (updatedCart.phoneCartDTOSList[i].model === productModel) {
                console.log("Chuỗi giống nhau tại: " + i);
                console.log('updatedCart.phoneCartDTOSList[i].model: ' + updatedCart.phoneCartDTOSList[i].model+' ; ' +productModel);
                // dùng hàm splice Xóa phần tử i trong phoneCartDTOSList nằm trong đối tượng updatedCart
                updatedCart.phoneCartDTOSList.splice(i, 1);
                console.log("+++++++++++++++++++++++++++");
                console.log(updatedCart.phoneCartDTOSList);
                break;
            }
        }
        for (var i = 0; i < updatedCart.products.length; i++) {
            if (updatedCart.products[i].model === productModel) {
                //console.log("Chuỗi giống nhau tại : "  + i + ' /' + updatedCart.products[i].model +' ; ' +productModel);
                //console.log(updatedCart.products);
                // dùng hàm splice Xóa phần tử i trong phoneCartDTOSList nằm trong đối tượng updatedCart
                updatedCart.products.splice(i, 1);

                //console.log("+++++++++++++++++++++++++++");
                //console.log(updatedCart.products);
                break;
            }
        }
        // Cập nhật cookie với danh sách sản phẩm cập nhật
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

        // Lấy giá trị số lượng và giá từ hàng
        var quantity = parseInt($(this).val());
        var price = parseFloat(row.find('td .price').text().replace('$', ''));

        // Tính tổng giá trị cho hàng hiện tại
        var total = (quantity * price).toFixed(2);

        // Cập nhật giá trị Total của hàng
        row.find('.total').text('$' + total);

        // Nếu hàng đó được chọn, cập nhật tổng giá trị
        if (isChecked) {
            calculateTotalAmount();
        }
    });
}

/**
 * *******************************************
 * Load tất cả data-product-model vào data-product-model-js để lưu trữ
 * sau đó mới xử lý lấy từng model của từng hàng ra được
 * *******************************************
 * */
{// Chờ cho trang web được tải xong
    $(document).ready(function () {

        var cartCookie = getCookie("cartCookie");
        // Chuyển cartCookie thành Object updatedCart (nếu có)
        var updatedCart = cartCookie ? JSON.parse(atob(cartCookie)) : [];
        // console.log(updatedCart);
        updateCartCookie(updatedCart);


        // Tìm kiếm tất cả các thuộc tính `data-product-model`
        var rows = document.querySelectorAll('tbody tr');
        // Duyệt qua từng hàng dữ liệu
        for (var i = 0; i < rows.length; i++) {
            var productModel = rows[i].getAttribute('data-product-model');
            rows[i].setAttribute('data-product-model-js', productModel);
        }
        // Tạo một hàm JavaScript để làm tròn giá trị
    });
}

/**
 * *******************************************
 * // Cập nhật cookie với danh sách sản phẩm cập nhật
 * *******************************************
 * */
{// Cập nhật cookie với danh sách sản phẩm cập nhật
    function updateCartCookie(updatedCart) {
        // Chuyển đổi cart thành chuỗi JSON string
        var cartJson = JSON.stringify(updatedCart);
        // Mã hóa Object thành Base64
        var cartBase64 = btoa(cartJson);
        // Lưu thông tin vào cookie
        document.cookie = "cartCookie=" + cartBase64 + "; expires=Thu, 31 Dec 2099 23:59:59 UTC; ";

        /*// Tạo một cookie để lưu trữ thông tin giỏ hàng
        document.cookie = "cartCookie=" + encodedCartJson + "; max-age=" + 7 * 24 * 60 * 60;*/
    }
}
/**
 * *******************************************
 * // Select All checkbox
 * *******************************************
 * */
{
// Lấy checkbox SelectAll
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