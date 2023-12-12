package org.example.http.resources;

import com.sun.net.httpserver.HttpExchange;
import org.example.PerfectNumberJavaWeb;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Class used to define a HttpResource, be it to retrieve a file or arbitrary code execution
 */
public class HTMLResource extends HttpResource {

    private final String resourcePath;

    public HTMLResource(String method, String uri, String resourcePath) {
        super(method, uri);
        this.resourcePath = resourcePath;
    }

    public void onRequest(HttpExchange request) throws HttpResourceException {
        OutputStream responseStream = request.getResponseBody();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try(InputStream dataStream = PerfectNumberJavaWeb.class.getClassLoader().getResourceAsStream(resourcePath)) {
            byte[] buf = new byte[1024 * 1024 * 8];
            int read;
            while ((read = dataStream.read(buf, 0, buf.length)) != -1) {
                os.write(buf, 0, read);
            }

            byte[] response = os.toByteArray();

            request.getResponseHeaders().add("Content-Type", "text/html");
            request.sendResponseHeaders(200, response.length);
            responseStream.write(response);
        } catch (IOException e) {
            throw new HttpResourceException(e);
        }


    }
}
