package exceptions;

class ValidationException extends RuntimeException{
    public ValidationException(String message){
        super(message);
    }
}