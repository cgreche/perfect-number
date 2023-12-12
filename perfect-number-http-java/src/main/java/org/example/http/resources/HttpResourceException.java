package org.example.http.resources;

/**
 * Generic class used as an exception related to a Http resource
 */
public class HttpResourceException extends Exception {

    public HttpResourceException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public HttpResourceException(Throwable cause) {
        super(cause);
    }
}
