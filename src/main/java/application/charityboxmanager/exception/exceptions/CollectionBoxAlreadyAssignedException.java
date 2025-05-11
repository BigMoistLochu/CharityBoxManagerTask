package application.charityboxmanager.exception.exceptions;

public class CollectionBoxAlreadyAssignedException extends RuntimeException {

    public CollectionBoxAlreadyAssignedException() {
        super("This collection box is already assigned to a fundraising event.");
    }

    public CollectionBoxAlreadyAssignedException(String message) {
        super(message);
    }
}