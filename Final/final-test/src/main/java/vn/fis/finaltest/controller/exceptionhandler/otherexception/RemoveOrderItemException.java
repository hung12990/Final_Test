package vn.fis.finaltest.controller.exceptionhandler.otherexception;

import vn.fis.finaltest.model.Status;

public class RemoveOrderItemException extends RuntimeException{
    public RemoveOrderItemException(Status status) {
        super(String.format("Chỉ được xoá orderItem khi order có trạng thái là %s",status.name()));
    }
}
