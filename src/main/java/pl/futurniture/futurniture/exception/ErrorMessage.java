package pl.futurniture.futurniture.exception;

public enum ErrorMessage {

    NOT_FOUND("Resource does not exists");

    private String message;

    ErrorMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
