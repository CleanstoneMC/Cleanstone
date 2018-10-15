package rocks.cleanstone.game.command.parameter;

import org.springframework.stereotype.Component;
import rocks.cleanstone.game.command.completion.CompletionContext;

import javax.annotation.Nullable;

@Component
public class FloatParameter implements CommandParameter<Float> {
    @Nullable
    @Override
    public Float get(CompletionContext<Float> context) {
        try {
            final float number = Float.parseFloat(context.getInput());
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
