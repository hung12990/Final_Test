package vn.fis.finaltest.controller.exceptionhandler.notfoundexception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(Long orderId) {
        super(String.format("Không tìm thấy đơn hàng có id = %s",orderId));
    }
}
