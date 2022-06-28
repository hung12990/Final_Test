package vn.fis.finaltest.controller.exceptionhandler.otherexception;


public class RemoveOrderStatusException extends RuntimeException{

    public RemoveOrderStatusException(Long orderId){
        super(String.format("Không thể xoá đơn hàng có id = %s vì đang ở trạng thái PAID",orderId));
    }
}
