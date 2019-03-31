public class BadInputException extends Exception {
    BadInputException(String message){
        super(message);
        new FileManager().writeErrors(message);
    }
}
