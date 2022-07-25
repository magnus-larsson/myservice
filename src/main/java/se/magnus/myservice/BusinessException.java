package se.magnus.myservice;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}