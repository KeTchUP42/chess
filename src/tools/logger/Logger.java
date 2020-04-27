package tools.logger;

import tools.logger.src.AbstractLogger;
import tools.logger.src.LoggerInterface;

/**
 * @author Roman
 */
public class Logger extends AbstractLogger {

    public static final LoggerInterface globalLogger = new Logger();

    public Logger(String logFilePath) {
        super(logFilePath);
    }

    public Logger() {
        super();
    }

    /**
     * global tools.logger configure
     *
     * @param logFilePath New log file path
     */
    public static void configureGlobalLogger(String logFilePath) {
        Logger logger = (Logger) Logger.globalLogger;
        logger.setLogPath(logFilePath);
        Logger.globalLogger.info("LOG_STARTED");
    }
}
