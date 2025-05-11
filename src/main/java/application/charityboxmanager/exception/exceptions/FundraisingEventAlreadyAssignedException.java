package application.charityboxmanager.exception.exceptions;

public class FundraisingEventAlreadyAssignedException extends RuntimeException {

    public FundraisingEventAlreadyAssignedException() {
        super("This fundraising event already has a collection box assigned.");
    }

    public FundraisingEventAlreadyAssignedException(String message) {
        super(message);
    }
}