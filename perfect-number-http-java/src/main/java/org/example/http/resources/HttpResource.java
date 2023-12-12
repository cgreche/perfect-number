package org.example.http.resources;

import com.sun.net.httpserver.HttpExchange;

/**
 * Class used to define a HttpResource, be it to retrieve a file or arbitrary code execution
 */
public abstract class HttpResource {
    protected final String method;
    protected final String uri;

    public HttpResource(String method, String uri) {
        this.method = method.toUpperCase();
        this.uri = uri;
    }

    public String method() {
        return this.method;
    }

    public String uri() {
        return this.uri;
    }

    public abstract void onRequest(HttpExchange request) throws HttpResourceException;
}
