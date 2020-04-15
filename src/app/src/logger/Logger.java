package app.src.logger;

import app.src.logger.src.AbstractLogger;
import app.src.logger.src.LoggerInterface;

/**
 * @author Roman
 */
public class Logger extends AbstractLogger {

    /**
     * Глобальный logger
     */
    public static final LoggerInterface globalLogger = new Logger();

    /**
     * Конструктор для произвольного пути к log файлу
     *
     * @param logFilePath Путь к лог файлу
     */
    public Logger(String logFilePath) {
        super(logFilePath);
    }

    /**
     * Конструктор для стандартного пути к log файлу
     */
    public Logger() {
        super();
    }

    /**
     * Метод конфигурации global logger
     *
     * @param logFilePath Новый путь к log файлу
     */
    public static void configureGlobalLogger(String logFilePath) {
        Logger logger = (Logger) Logger.globalLogger;
        logger.setLogPath(logFilePath);
        Logger.globalLogger.info("LOG_STARTED");
    }
}
