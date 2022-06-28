package vn.fis.finaltest.controller.exceptionhandler.otherexception;

public class ProductQuantityNotEnoughException extends RuntimeException{
    public ProductQuantityNotEnoughException() {
        super("Số lượng không đủ để thêm với sản phẩm có id = %");
    }
}
