package application.charityboxmanager.exception.exceptions;

public class CollectionBoxNotEmptyException extends RuntimeException{

    public CollectionBoxNotEmptyException() {
        super("You can only assign a collection box to a fundraising event if the collection box is empty.");
    }

    public CollectionBoxNotEmptyException(String message) {
        super(message);
    }

}
