package rocks.cleanstone.game.command.parameter;

import javax.annotation.Nullable;
import rocks.cleanstone.game.command.completion.CompletionContext;

public class FloatParameter implements CommandParameter<Float> {
    @Nullable
    @Override
    public Float get(CompletionContext<Float> context) {
        try {
            float number = Float.parseFloat(context.getInput());
            if (!Float.isFinite(number) || Math.abs(number) >= 3.2e7) return null;
            return number;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public Class<Float> getParameterClass() {
        return Float.class;
    }
}
