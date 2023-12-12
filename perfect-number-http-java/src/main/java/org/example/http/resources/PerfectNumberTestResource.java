package org.example.http.resources;

import com.sun.net.httpserver.HttpExchange;
import org.example.PerfectNumber;
import org.example.http.HttpUtils;
import org.example.http.resources.HttpResource;
import org.example.http.resources.HttpResourceException;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Map;

public class PerfectNumberTestResource extends HttpResource {

    public PerfectNumberTestResource(String method, String uri) {
        super(method, uri);
    }

    @Override
    public void onRequest(HttpExchange request) throws HttpResourceException {
        Map<String, String> requestParams = HttpUtils.getRequestParams(request.getRequestURI());
        OutputStream responseStream = request.getResponseBody();

        try {
            String numberValue = requestParams.get("number");
            if(null == numberValue) {
                // Bad request (invalid param)
                request.sendResponseHeaders(400, 0);
                responseStream.close();
                return;
            }

            BigInteger number = new BigInteger(numberValue);
            boolean result = PerfectNumber.test(number);

            String stringLiteralResult = String.valueOf(result);

            request.getResponseHeaders().add("Content-Type", "application/json");
            request.sendResponseHeaders(200, stringLiteralResult.getBytes().length);
            responseStream.write(stringLiteralResult.getBytes());
        } catch (IOException e) {
            throw new HttpResourceException(e);
        }
    }
}