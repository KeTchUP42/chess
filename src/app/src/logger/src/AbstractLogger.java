package app.src.logger.src;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author Roman
 */
public abstract class AbstractLogger implements LoggerInterface {

    /**
     * Стандартный путь к файлу лога
     */
    protected String logFilePath;

    /**
     * @param logFilePath String
     */
    public AbstractLogger(@NotNull String logFilePath) {
        this.logFilePath = logFilePath;
    }

    /**
     * Конструктор для стандартного пути к гол файлу
     */
    public AbstractLogger() {
        this.logFilePath = STANDARD_LOG_FILE_PATH;
    }

    /**
     * System is unusable.
     *
     * @param message Сообщение
     */
    @Override
    public void emergency(String message) {
        this.writeLogToFile("<EMERGENCY> " + message + " <EMERGENCY>\n");
    }

    /**
     * Action must be taken immediately.
     * <p>
     * Example: Entire website down, database unavailable, etc. This should
     * trigger the SMS alerts and wake you up.
     *
     * @param message Сообщение
     */
    @Override
    public void alert(String message) {
        this.writeLogToFile("<ALERT> " + message + " <ALERT>\n");
    }

    /**
     * Critical conditions.
     * <p>
     * Example: Application component unavailable, unexpected exception.
     *
     * @param message Сообщение
     */
    @Override
    public void critical(String message) {
        this.writeLogToFile("<CRITICAL> " + message + " <CRITICAL>\n");
    }

    /**
     * Runtime errors that do not require immediate action but should typically
     * be logged and monitored.
     *
     * @param message Сообщение
     */
    @Override
    public void error(String message) {
        this.writeLogToFile("<ERROR> " + message + " <ERROR>\n");
    }

    /**
     * Exceptional occurrences that are not errors.
     * <p>
     * Example: Use of deprecated APIs, poor use of an API, undesirable things
     * that are not necessarily wrong.
     *
     * @param message Сообщение
     */
    @Override
    public void warning(String message) {
        this.writeLogToFile("<WARNING> " + message + " <WARNING>\n");
    }

    /**
     * Normal but significant events.
     *
     * @param message Сообщение
     */
    @Override
    public void notice(String message) {
        this.writeLogToFile("NOTICE: " + message + "\n");
    }

    /**
     * Interesting events.
     * <p>
     * Example: User logs in, SQL logs.
     *
     * @param message Сообщение
     */
    @Override
    public void info(String message) {
        this.writeLogToFile("INFO: " + message + "\n");
    }

    /**
     * Detailed debug information.
     *
     * @param message Сообщение
     */
    @Override
    public void debug(String message) {
        this.writeLogToFile("DEBUG: " + message + "\n");
    }

    /**
     * @param logPath Новый путь к лог файлу
     */
    public void setLogPath(String logPath) {
        this.logFilePath = logPath;
    }

    /**
     * Общий метод записи лога
     *
     * @param message Сообщение лога
     */
    protected void writeLogToFile(String message) {
        try {
            File file = new File(this.logFilePath);
            if (!file.exists() || !file.isFile() || !file.canWrite()) {
                this.setLogPath(STANDARD_LOG_FILE_PATH);
                try {
                    Files.createFile(Paths.get(this.logFilePath));
                } catch (IOException ignored) {
                }
            }
            Files.write(Paths.get(this.logFilePath), message.getBytes(), StandardOpenOption.APPEND);
        } catch (Exception ignored) {
        }
    }
}
