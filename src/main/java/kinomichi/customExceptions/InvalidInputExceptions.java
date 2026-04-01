package kinomichi.customExceptions;

public class InvalidInputExceptions extends RuntimeException {
    public InvalidInputExceptions(String message) {
        super(message);
    }
    public InvalidInputExceptions(Exception e) {super(e.getMessage());}
}
