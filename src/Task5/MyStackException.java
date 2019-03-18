package Task5;

public class MyStackException extends Exception{


    public MyStackException(String message) {
        super("Unsuccessful push operation..."+message);
    }

}