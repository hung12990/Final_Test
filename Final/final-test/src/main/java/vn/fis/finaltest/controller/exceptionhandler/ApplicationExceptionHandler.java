package vn.fis.finaltest.controller.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import vn.fis.finaltest.constant.Constant;
import vn.fis.finaltest.controller.exceptionhandler.notfoundexception.CustomerNotFoundException;
import vn.fis.finaltest.controller.exceptionhandler.notfoundexception.OrderItemNotFoundException;
import vn.fis.finaltest.controller.exceptionhandler.notfoundexception.OrderNotFoundException;
import vn.fis.finaltest.controller.exceptionhandler.notfoundexception.ProductNotFoundException;
import vn.fis.finaltest.controller.exceptionhandler.otherexception.*;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            CustomerNotFoundException.class
    })
    protected ResponseEntity<ErrorMessage> handleCustomerNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder()
                        .code(Constant.CUSTOMER_NOT_FOUND_EXCEPTION)
                        .message(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {
            OrderNotFoundException.class
    })
    protected ResponseEntity<ErrorMessage> handleOrderNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder()
                        .code(Constant.ORDER_NOT_FOUND_EXCEPTION)
                        .message(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {
            ProductQuantityNotEnoughException.class
    })
    protected ResponseEntity<ErrorMessage> handleProductQuantityNotEnoughException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder()
                        .code(Constant.PRODUCT_QUANTITY_NOT_ENOUGH_EXCEPTION)
                        .message(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {
            ProductNotFoundException.class
    })
    protected ResponseEntity<ErrorMessage> handleProductNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder()
                        .code(Constant.PRODUCT_NOT_FOUND_EXCEPTION)
                        .message(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {
            ConvertSatusCreatedToPaidException.class
    })
    protected ResponseEntity<ErrorMessage> handleConvertSatusCreatedToPaidException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder()
                        .code(Constant.CONVERT_STATUS_CREATED_TO_PAID_EXCEPTION)
                        .message(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {
            ConvertSatusCreatedToCancelledException.class
    })
    protected ResponseEntity<ErrorMessage> handleConvertSatusCreatedToCalcelledException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder()
                        .code(Constant.CONVERT_STATUS_CREATED_TO_CANCELLED_EXCEPTION)
                        .message(exception.getMessage()).build());
    }
    @ExceptionHandler(value = {
            AddOrderItemException.class
    })
    protected ResponseEntity<ErrorMessage> handleAddOrderItemException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder()
                        .code(Constant.ADD_ORDER_ITEM_EXCEPTION)
                        .message(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {
            RemoveOrderItemException.class
    })
    protected ResponseEntity<ErrorMessage> handleRemoveOrderItemException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder()
                        .code(Constant.REMOVE_ORDER_ITEM_EXCEPTION)
                        .message(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {
            RemoveOrderStatusException.class
    })
    protected ResponseEntity<ErrorMessage> handleRemoveOrderStatusException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder()
                        .code(Constant.REMOVE_ORDER_STATUS_EXCEPTION)
                        .message(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {
            OrderItemNotFoundException.class
    })
    protected ResponseEntity<ErrorMessage> handleOrderItemNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().code(Constant.ORDER_ITEM_NOT_FOUND_EXCEPTION).message(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {
            CustomerMobileException.class
    })
    protected ResponseEntity<ErrorMessage> handleCustomerMobileException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder()
                        .code(Constant.CUSTOMER_MOBILE_EXCEPTION)
                        .message(exception.getMessage()).build());
    }
}
