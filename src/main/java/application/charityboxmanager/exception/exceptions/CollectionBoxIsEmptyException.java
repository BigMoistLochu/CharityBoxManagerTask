package application.charityboxmanager.exception.exceptions;

public class CollectionBoxIsEmptyException extends RuntimeException{

    public CollectionBoxIsEmptyException() {
        super("CollectionBox is Empty, you cannot transfer money");
    }

    public CollectionBoxIsEmptyException(String message) {
        super(message);
    }
}
