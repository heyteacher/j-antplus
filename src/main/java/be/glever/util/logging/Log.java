package be.glever.util.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

/**
 * Logger util to allow for SLF4J lambda params until SLF4J 2.0.0 is released.
 * It was easier to write this util than to switch logging frameworks which do support lambdas.
 */
public class Log {

    private final Logger logger;

    private static Logger androidLogger;
    private Log(Logger logger) {
        this.logger = logger;
    }

    /**
     * Returns the logger.
     *
     * @param clazz the clazz
     * @return the logger.
     */
    public static Log getLogger(Class<?> clazz) {
        return new Log(LoggerFactory.getLogger(clazz));
    }

    /**
     * Sets the android logger.
     *
     * @param logger the logger
     */
    public static void setAndroidLogger(Logger logger) {
        androidLogger = logger;
    }
    /**
     * Debug.
     *
     * @param msgSupplier the msg supplier
     */
    public void debug(Supplier<String> msgSupplier) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(msgSupplier.get());
            if (androidLogger != null) {
                androidLogger.debug("{} - {}", this.logger.getName(), msgSupplier.get());
            }
        }
    }

    /**
     * Info.
     *
     * @param msgSupplier the msg supplier
     */
    public void info(Supplier<String> msgSupplier) {
        if (this.logger.isInfoEnabled()) {
            this.logger.info(msgSupplier.get());
            if (androidLogger != null) {
                androidLogger.info("{} - {}", this.logger.getName(), msgSupplier.get());
            }
        }
    }

    /**
     * Warn.
     *
     * @param msgSupplier the msg supplier
     */
    public void warn(Supplier<String> msgSupplier) {
        if (this.logger.isWarnEnabled()) {
            this.logger.warn(msgSupplier.get());
            if (androidLogger != null) {
                androidLogger.warn("{} - {}", this.logger.getName(), msgSupplier.get());
            }
        }
    }

    /**
     * Error.
     *
     * @param msgSupplier the msg supplier
     */
    public void error(Supplier<String> msgSupplier) {
        if (this.logger.isErrorEnabled()) {
            this.logger.error(msgSupplier.get());
            if (androidLogger != null) {
                androidLogger.error("{} - {}", this.logger.getName(), msgSupplier.get());
            }
        }
    }

    /**
     * Error.
     *
     * @param msgSupplier the msg supplier
     * @param t the t
     */
    public void error(Supplier<String> msgSupplier, Throwable t) {
        if (this.logger.isErrorEnabled()) {
            this.logger.error(msgSupplier.get(), t);
            if (androidLogger != null) {
                androidLogger.error("{} - {}", this.logger.getName(), msgSupplier.get(), t);
            }
        }
    }
}
