package rocks.cleanstone.game.command;

public class InvalidParameterException extends CommandException {
    private final String givenString;
    private final Class requiredParameter;

    public InvalidParameterException(String givenString, Class requiredParameter) {
        this.givenString = givenString;
        this.requiredParameter = requiredParameter;
    }

    public String getGivenString() {
        return givenString;
    }

    public Class getRequiredParameter() {
        return requiredParameter;
    }
}
