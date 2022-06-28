package vn.fis.finaltest.controller.exceptionhandler.otherexception;

import vn.fis.finaltest.model.Status;

public class ConvertSatusCreatedToCancelledException extends RuntimeException{
    public ConvertSatusCreatedToCancelledException(Status status) {
        super(String.format("lỗi chuyển trạng thái %s sang trạng thái CANCELLED",status.name()));
    }
}
