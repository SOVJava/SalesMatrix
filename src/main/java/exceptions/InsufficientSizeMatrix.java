package exceptions;

public class InsufficientSizeMatrix extends Exception{

    public InsufficientSizeMatrix() {
    }

    public InsufficientSizeMatrix(String message){
        super(message);
    }
}
