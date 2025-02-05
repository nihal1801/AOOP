import java.util.ArrayList;
import java.util.List;

// LogLevel enum
enum LogLevel {
    INFO, DEBUG, ERROR
}

// Command interface
interface Command {
    void execute(String message);
}

// LogCommand class
class LogCommand implements Command {
    private LogLevel level;
    private String message;

    public LogCommand(LogLevel level, String message) {
        this.level = level;
        this.message = message;
    }

    @Override
    public void execute(String message) {
        System.out.println("Log Level: " + level + ", Message: " + message);
    }

    public String getMessage() { 
        return message;
    }
}

// Abstract LogHandler class
abstract class LogHandler {
    private LogHandler nextHandler;

    public void setNextHandler(LogHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    protected abstract void handle(LogLevel level, String message);

    public void process(LogLevel level, String message) {
        if (canHandle(level)) {
            handle(level, message);
        } else if (nextHandler != null) {
            nextHandler.process(level, message);
        }
    }

    protected abstract boolean canHandle(LogLevel level);
}

// InfoHandler class
class InfoHandler extends LogHandler {
    @Override
    protected void handle(LogLevel level, String message) {
        if (level == LogLevel.INFO) {
            System.out.println("INFO: " + message);
        }
    }

    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.INFO;
    }
}

// DebugHandler class
class DebugHandler extends LogHandler {
    @Override
    protected void handle(LogLevel level, String message) {
        if (level == LogLevel.DEBUG) {
            System.out.println("DEBUG: " + message);
        }
    }

    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.DEBUG;
    }
}

// ErrorHandler class
class ErrorHandler extends LogHandler {
    @Override
    protected void handle(LogLevel level, String message) {
        if (level == LogLevel.ERROR) {
            System.out.println("ERROR: " + message);
        }
    }

    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.ERROR;
    }
}

// Logger class
class Logger {
    private List<LogCommand> commands; // Change to List<LogCommand>

    public Logger() {
        commands = new ArrayList<>();
    }

    public void addCommand(Command command) {
        if (command instanceof LogCommand) {
            commands.add((LogCommand) command); 
        } else {
            System.err.println("Invalid command type. Expected LogCommand.");
        }
    }

    public void processLogs() {
        for (LogCommand command : commands) { 
            command.execute(command.getMessage()); 
        }
    }
}

// Client class
class Client {
    public static void main(String[] args) {
        InfoHandler infoHandler = new InfoHandler();
        DebugHandler debugHandler = new DebugHandler();
        ErrorHandler errorHandler = new ErrorHandler();

        infoHandler.setNextHandler(debugHandler);
        debugHandler.setNextHandler(errorHandler);

        Command infoCommand = new LogCommand(LogLevel.INFO, "This is an info message.");
        Command debugCommand = new LogCommand(LogLevel.DEBUG, "This is a debug message.");
        Command errorCommand = new LogCommand(LogLevel.ERROR, "This is an error message.");

        Logger logger = new Logger();
        logger.addCommand(infoCommand);
        logger.addCommand(debugCommand);
        logger.addCommand(errorCommand);

        logger.processLogs();
    }
}