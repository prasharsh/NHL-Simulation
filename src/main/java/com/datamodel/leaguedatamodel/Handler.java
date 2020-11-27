package com.datamodel.leaguedatamodel;
import org.apache.log4j.Logger;

public class Handler implements Thread.UncaughtExceptionHandler {

    private static Logger LOGGER = Logger.getLogger(Handler.class);

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        LOGGER.error("Unhandled exception caught: "+e.getLocalizedMessage());
    }
}