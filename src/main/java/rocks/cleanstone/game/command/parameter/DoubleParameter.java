package rocks.cleanstone.game.command.parameter;

import org.springframework.stereotype.Component;
import rocks.cleanstone.game.command.completion.CompletionContext;

import javax.annotation.Nullable;

@Component
public class DoubleParameter implements CommandParameter<Double> {
    @Nullable
    @Override
    public Double get(CompletionContext<Double> context) {
        try {
            final double number = Double.parseDouble(context.getInput());
            if (!Double.isFinite(number) || Math.abs(number) >= 3.2e7) return null;
            return number;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public Class<Double> getParameterClass() {
        return Double.class;
    }
}
