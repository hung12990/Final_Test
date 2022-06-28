package vn.fis.finaltest.controller.exceptionhandler.notfoundexception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(Long customerId) {
        super(String.format("Không tìm thấy khách hàng có id = %s",customerId));
    }
}
