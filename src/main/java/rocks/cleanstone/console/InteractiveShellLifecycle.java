package rocks.cleanstone.console;

import lombok.extern.slf4j.Slf4j;
import org.jline.reader.LineReader;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.InputProvider;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.CleanstoneServer;

import java.io.IOException;

@Slf4j
@Component
public class InteractiveShellLifecycle implements SmartLifecycle, Runnable {

    private final LineReader lineReader;

    private final PromptProvider promptProvider;

    private final Shell shell;

    private boolean running;

    private final Thread thread;

    @Lazy
    public InteractiveShellLifecycle(LineReader lineReader, PromptProvider promptProvider, Shell shell) {
        this.lineReader = lineReader;
        this.promptProvider = promptProvider;
        this.shell = shell;
        this.thread = new Thread(this);
    }

    @Override
    public void start() {
        thread.start();
        running = true;
    }

    @Override
    public boolean isAutoStartup() {
        return false;
    }

    @Override
    public void stop() {
//        if (shell != null) {
//            shell.evaluate(() -> "/quit");
//        }
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public void run() {
        InputProvider inputProvider = new InteractiveShellApplicationRunner.JLineInputProvider(lineReader, promptProvider);
        try {
            shell.run(inputProvider);
        } catch (IOException e) {
            log.error("Error in Shell Subsystem", e);
            CleanstoneServer.stop();
        }
    }
}
