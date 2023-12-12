package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.example.http.HttpUtils;
import org.example.http.resources.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class PerfectNumberJavaWeb {

    static class MyHttpHandler implements HttpHandler {

        private final Map<String, HttpResource> httpResources = new HashMap<>();

        public MyHttpHandler() {
            addResource(new HTMLResource("GET", "/", "index.html"));
            addResource(new PerfectNumberTestResource("GET", "/perfect-number/test"));
            addResource(new PerfectNumberFindBetweenResource("GET", "/perfect-number/find"));
        }

        public void addResource(HttpResource resource) {
            String key = resource.method() + resource.uri();
            httpResources.put(key, resource);
        }

        @Override
        public void handle(HttpExchange request) throws IOException {
            String requestURI = request.getRequestURI().getPath();
            String method = request.getRequestMethod();

            OutputStream responseStream = request.getResponseBody();

            try {
                String resourceKey = method + requestURI;
                HttpResource httpResource = httpResources.get(resourceKey);
                if(null == httpResource) {
                    // Resource not found
                    request.sendResponseHeaders(404, 0);
                    return;
                }

                httpResource.onRequest(request);
            } catch (HttpResourceException e) {
                // Internal error
                request.sendResponseHeaders(500, 0);
            } finally {
                responseStream.close();
            }
        }
    }

    public static void main(String... args) throws IOException {
        MyHttpHandler myHttpHandler = new MyHttpHandler();
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", myHttpHandler);
        server.start();
    }
}