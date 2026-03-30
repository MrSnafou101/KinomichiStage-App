package kinomichi.customExceptions;

public class WrongDateFormat extends RuntimeException {
    public WrongDateFormat(String message) {
        super(message);
    }
    public WrongDateFormat(Exception e) {super(e);}
}
