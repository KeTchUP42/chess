package bgs.tools.logger.src;

import org.jetbrains.annotations.NotNull;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Roman
 */
public abstract class AbstractLogger implements LoggerInterface {

    protected FileWriter fileWriter;

    public AbstractLogger(@NotNull String logFilePath) {
        this.setLogWriter(logFilePath);
    }

    public AbstractLogger() {
        this.setLogWriter(STANDARD_LOG_FILE_PATH);
    }

    @Override
    public void emergency(String message) {
        this.writeLogToFile(" EMERGENCY: " + message + "\n");
    }

    @Override
    public void alert(String message) {
        this.writeLogToFile(" ALERT: " + message + "\n");
    }

    @Override
    public void critical(String message) {
        this.writeLogToFile(" CRITICAL: " + message + "\n");
    }

    @Override
    public void error(String message) {
        this.writeLogToFile(" ERROR: " + message + "\n");
    }

    @Override
    public void warning(String message) {
        this.writeLogToFile(" WARNING: " + message + "\n");
    }

    @Override
    public void notice(String message) {
        this.writeLogToFile(" NOTICE: " + message + "\n");
    }

    @Override
    public void info(String message) {
        this.writeLogToFile(" INFO: " + message + "\n");
    }

    @Override
    public void debug(String message) {
        this.writeLogToFile(" DEBUG: " + message + "\n");
    }

    public void setLogWriter(String logFilePath) {
        try {
            this.fileWriter = new FileWriter(logFilePath, true);
        } catch (IOException ignored) {
            this.setLogWriter(STANDARD_LOG_FILE_PATH);
        }
    }

    /**
     * Log writer
     *
     * @param message log message
     */
    protected void writeLogToFile(String message) {
        message = this.generateDateTimeString() + message;
        try {
            this.fileWriter.write(message);
            this.fileWriter.flush();
        } catch (IOException exception) {
            this.setLogWriter(STANDARD_LOG_FILE_PATH);
            this.writeLogToFile(exception.getMessage());
        }
    }

    /**
     * Method returns date and time in string
     *
     * @return String
     */
    protected String generateDateTimeString() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
