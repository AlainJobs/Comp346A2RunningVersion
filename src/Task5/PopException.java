package Task5;

public class PopException extends Exception {
    public PopException()
    {
        super("Canot pop. The stack is empty.");
    }
}