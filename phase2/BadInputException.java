public class BadInputException extends Exception {
    BadInputException(String message){
        super(message);
        ATM_machine.fileManager.writeErrors(message);
    }
}
