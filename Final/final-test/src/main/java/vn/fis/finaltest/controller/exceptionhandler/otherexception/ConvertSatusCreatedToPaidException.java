package vn.fis.finaltest.controller.exceptionhandler.otherexception;

import vn.fis.finaltest.model.Status;

public class ConvertSatusCreatedToPaidException extends RuntimeException{
    public ConvertSatusCreatedToPaidException(Status status) {
        super(String.format("lỗi chuyển trạng thái %s sang trạng thái PAID",status.name()));
    }
}
