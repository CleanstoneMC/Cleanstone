package rocks.cleanstone.game.command;

public class NotEnoughParametersException extends CommandException {

    private final int givenAmount, requiredAmount;

    public NotEnoughParametersException(int givenAmount, int requiredAmount) {
        this.givenAmount = givenAmount;
        this.requiredAmount = requiredAmount;
    }

    public int getGivenAmount() {
        return givenAmount;
    }

    public int getRequiredAmount() {
        return requiredAmount;
    }
}
