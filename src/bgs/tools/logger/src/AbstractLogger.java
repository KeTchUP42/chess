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
        this.log(" EMERGENCY: " + message + "\n");
    }

    @Override
    public void alert(String message) {
        this.log(" ALERT: " + message + "\n");
    }

    @Override
    public void critical(String message) {
        this.log(" CRITICAL: " + message + "\n");
    }

    @Override
    public void error(String message) {
        this.log(" ERROR: " + message + "\n");
    }

    @Override
    public void warning(String message) {
        this.log(" WARNING: " + message + "\n");
    }

    @Override
    public void notice(String message) {
        this.log(" NOTICE: " + message + "\n");
    }

    @Override
    public void info(String message) {
        this.log(" INFO: " + message + "\n");
    }

    @Override
    public void debug(String message) {
        this.log(" DEBUG: " + message + "\n");
    }

    public void setLogWriter(String logFilePath) {
        try {
            this.fileWriter = new FileWriter(logFilePath, true);
        } catch (IOException ignored) {
            this.setLogWriter(STANDARD_LOG_FILE_PATH);
        }
    }

    /**
     * Method write message to log file
     */
    protected void log(String message) {
        message = this.generateDateTimeString() + message;
        try {
            this.fileWriter.write(message);
            this.fileWriter.flush();
        } catch (IOException exception) {
            this.setLogWriter(STANDARD_LOG_FILE_PATH);
            this.log(exception.getMessage());
        }
    }

    /**
     * Method return current date and time in string format
     */
    protected String generateDateTimeString() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
