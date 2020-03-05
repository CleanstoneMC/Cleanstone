package rocks.cleanstone.console;

import org.jline.reader.History;
import org.jline.reader.LineReader;
import org.jline.reader.impl.history.DefaultHistory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;

@Component
public class CleanstoneCommandHistory {
    @Primary
    @Bean
    public History history(LineReader lineReader) {
        lineReader.setVariable(LineReader.HISTORY_FILE, Paths.get("history.log"));
        return new DefaultHistory(lineReader);
    }
}
