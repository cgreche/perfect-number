package org.example.http.resources;

import com.sun.net.httpserver.HttpExchange;
import org.example.PerfectNumber;
import org.example.http.HttpUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class PerfectNumberFindBetweenResource extends HttpResource {

    public PerfectNumberFindBetweenResource(String method, String uri) {
        super(method, uri);
    }

    @Override
    public void onRequest(HttpExchange request) throws HttpResourceException {
        Map<String, String> requestParams = HttpUtils.getRequestParams(request.getRequestURI());
        OutputStream responseStream = request.getResponseBody();

        try {
            String number1Value = requestParams.get("number1");
            String number2Value = requestParams.get("number2");
            if(null == number1Value || null == number2Value) {
                // Bad request (invalid params)
                request.sendResponseHeaders(400, 0);
                return;
            }

            try {
                BigInteger number1 = new BigInteger(number1Value);
                BigInteger number2 = new BigInteger(number2Value);
                List<BigInteger> result = PerfectNumber.findBetween(number1, number2);

                String stringLiteralResult = String.valueOf(result);

                request.getResponseHeaders().add("Content-Type", "application/json");
                request.sendResponseHeaders(200, stringLiteralResult.getBytes().length);
                responseStream.write(stringLiteralResult.getBytes());
            } catch(NumberFormatException e) {
                // Bad request (not a number)
                request.sendResponseHeaders(400, 0);
            }
        } catch (IOException e) {
            throw new HttpResourceException(e);
        }
    }
}