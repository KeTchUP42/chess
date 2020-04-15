package app.src.logger.src;

/**
 * @author psr4
 */
public interface LoggerInterface {

    /**
     * Стандартный путь к лог файлу
     */
    String STANDARD_LOG_FILE_PATH = "log.txt";

    /**
     * System is unusable.
     */
    void emergency(String message);

    /**
     * Action must be taken immediately.
     * <p>
     * Example: Entire website down, database unavailable, etc. This should
     * trigger the SMS alerts and wake you up.
     */
    void alert(String message);

    /**
     * Critical conditions.
     * <p>
     * Example: Application component unavailable, unexpected exception.
     */
    void critical(String message);

    /**
     * Runtime errors that do not require immediate action but should typically
     * be logged and monitored.
     */
    void error(String message);

    /**
     * Exceptional occurrences that are not errors.
     * <p>
     * Example: Use of deprecated APIs, poor use of an API, undesirable things
     * that are not necessarily wrong.
     */
    void warning(String message);

    /**
     * Normal but significant events.
     */
    void notice(String message);

    /**
     * Interesting events.
     * <p>
     * Example: User logs in, SQL logs.
     */
    void info(String message);

    /**
     * Detailed debug information.
     */
    void debug(String message);
}
