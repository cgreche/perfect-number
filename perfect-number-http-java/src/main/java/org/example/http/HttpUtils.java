package org.example.http;

import com.sun.net.httpserver.HttpExchange;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Class used to define a HttpResource, be it to retrieve a file or arbitrary code execution
 */
public abstract class HttpUtils {

    /**
     * Simple and unsecure URI param splitter
     * @param requestURI
     * @return Map containing unique key-value pairs
     */
    public static Map<String, String> getRequestParams(URI requestURI) {
        String query = requestURI.getQuery();

        if(null == query)
            return Collections.emptyMap();

        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            result.put(entry[0], entry[1]);
        }
        return result;
    }
}
