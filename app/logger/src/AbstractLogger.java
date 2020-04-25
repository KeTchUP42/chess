package logger.src;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Roman
 */
public abstract class AbstractLogger implements LoggerInterface {

    protected String logFilePath;

    public AbstractLogger(@NotNull String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public AbstractLogger() {
        this.logFilePath = STANDARD_LOG_FILE_PATH;
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

    public void setLogPath(String logPath) {
        this.logFilePath = logPath;
    }

    /**
     * Log writer
     *
     * @param message log message
     */
    protected void writeLogToFile(String message) {
        message = this.generateDateTimeString() + message;
        try {
            if (!new File(this.logFilePath).exists()) {
                try {
                    Files.createFile(Paths.get(this.logFilePath));
                } catch (IOException ignored) {
                }
            }
        } catch (Exception ignored) {
        }
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

    /**
     * Method returns date and time in string
     *
     * @return String
     */
    protected String generateDateTimeString() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
