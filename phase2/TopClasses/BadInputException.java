package TopClasses;

public class BadInputException extends Exception {
    public BadInputException(String message){
        super(message);
        new FileManager().writeErrors(message);
    }
}
