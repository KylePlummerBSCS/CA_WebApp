package com.revature.WebApp.exceptions;

public class DataSourceException extends RuntimeException {
    public DataSourceException(Throwable e) {
        super("There was a problem when communicating with the database. Check the logs for more details.", e);
    }

    public DataSourceException(String message) {
        super(message);
    }

}
