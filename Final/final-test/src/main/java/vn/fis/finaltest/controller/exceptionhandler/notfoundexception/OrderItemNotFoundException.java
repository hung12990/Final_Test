package vn.fis.finaltest.controller.exceptionhandler.notfoundexception;

public class OrderItemNotFoundException extends RuntimeException{
    public OrderItemNotFoundException(Long orderItemId) {
        super(String.format("Không tìm thấy orderItem có id = %s",orderItemId));
    }
}
