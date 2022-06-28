package vn.fis.finaltest.controller.exceptionhandler.otherexception;


public class CustomerMobileException extends RuntimeException{
    public CustomerMobileException(String mobile) {
        super(String.format("Số điện thoại %s đã tồn tại",mobile));
    }
}
