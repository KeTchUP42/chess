package logger;

import logger.src.AbstractLogger;
import logger.src.LoggerInterface;

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
     * global logger configure
     *
     * @param logFilePath New log file path
     */
    public static void configureGlobalLogger(String logFilePath) {
        Logger logger = (Logger) Logger.globalLogger;
        logger.setLogPath(logFilePath);
        Logger.globalLogger.info("LOG_STARTED");
    }
}
