package bgs.tools.logger.src;

/**
 * @author psr-4
 */
public interface LoggerInterface {

    String STANDARD_LOG_FILE_PATH = "source.log";

    /**
     * System is unusable.
     */
    void emergency(String message);

    /**
     * Action must be taken immediately.
     */
    void alert(String message);

    /**
     * Critical conditions.
     */
    void critical(String message);

    /**
     * Runtime errors that do not require immediate action but should typically
     * be logged and monitored.
     */
    void error(String message);

    /**
     * Exceptional occurrences that are not errors.
     */
    void warning(String message);

    /**
     * Normal but significant events.
     */
    void notice(String message);

    /**
     * Interesting events.
     */
    void info(String message);

    /**
     * Detailed debug information.
     */
    void debug(String message);
}
