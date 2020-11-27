package bgs.tools.logger;

import bgs.tools.logger.src.AbstractLogger;
import bgs.tools.logger.src.LoggerInterface;

/**
 * @author Roman
 */
public final class Logger extends AbstractLogger {

    private static LoggerInterface globalLogger;

    public Logger(String logFilePath) {
        super(logFilePath);
    }

    public Logger() {
        super();
    }

    /**
     * Global logger configuring
     *
     * @param logFilePath
     */
    public static void configureGlobalLogger(String logFilePath) {
        Logger.globalLogger = new Logger(logFilePath);
        Logger.globalLogger.info("Global logger configured.");
    }

    public static LoggerInterface getGlobalLogger() {
        if (Logger.globalLogger == null)
            Logger.configureGlobalLogger(STANDARD_LOG_FILE_PATH);
        return Logger.globalLogger;
    }
}
