package Task3;

public class PushException extends Exception {
    public PushException()
    {
        super("Canot push. The stack is full.");
    }
}