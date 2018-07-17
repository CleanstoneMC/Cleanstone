package rocks.cleanstone.game.command;

public class InvalidParameterException extends CommandException {
    private final String givenString;
    private final Class requiredParameter;
    private final int index;

    public InvalidParameterException(String givenString, Class requiredParameter, int index) {
        this.givenString = givenString;
        this.requiredParameter = requiredParameter;
        this.index = index;
    }

    public String getGivenString() {
        return givenString;
    }

    public Class getRequiredParameter() {
        return requiredParameter;
    }

    public int getIndex() {
        return index;
    }
}
