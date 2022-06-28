package vn.fis.finaltest.controller.exceptionhandler.notfoundexception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(Long productId) {
        super(String.format("Không tìm thấy sản phẩm có id = %s",productId));
    }
}
