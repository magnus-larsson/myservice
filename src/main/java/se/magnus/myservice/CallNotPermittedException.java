package se.magnus.myservice;

public class CallNotPermittedException extends RuntimeException {

    public CallNotPermittedException(String message) {
        super(message);
    }
}