package tools.logger;

import tools.logger.src.AbstractLogger;
import tools.logger.src.LoggerInterface;

/**
 * @author Roman
 */
public class Logger extends AbstractLogger {

    protected static LoggerInterface globalLogger;

    public Logger(String logFilePath) {
        super(logFilePath);
    }

    public Logger() {
        super();
    }

    /**
     * Global logger configuring
     *
     * @param logFilePath New log file path
     */
    public static void configureGlobalLogger(String logFilePath) {
        Logger.globalLogger = new Logger(logFilePath);
        Logger.globalLogger.info("GLOBAL_LOGGER_CONFIGURED");
    }

    public static LoggerInterface getGlobalLogger() {
        return Logger.globalLogger;
    }
}
