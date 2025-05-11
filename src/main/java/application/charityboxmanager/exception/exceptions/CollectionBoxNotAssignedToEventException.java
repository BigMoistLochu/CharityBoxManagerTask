package application.charityboxmanager.exception.exceptions;

public class CollectionBoxNotAssignedToEventException extends RuntimeException {

    public CollectionBoxNotAssignedToEventException() {
        super("Collection box must be assigned to a fundraising event before adding money.");
    }

    public CollectionBoxNotAssignedToEventException(String message) {
        super(message);
    }
}
