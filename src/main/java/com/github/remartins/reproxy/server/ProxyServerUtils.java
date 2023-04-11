package com.github.remartins.reproxy.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProxyServerUtils {

    public void updateStatusWithResponse(spark.Response res, int statusCode) {
        res.status(statusCode);
    }

    public void updateHeadersWithResponse(spark.Response res, Map<String, String> headers) {
        for (String header : headers.keySet()) {
            if (!header.equals("Content-Length")) res.header(header, headers.get(header));
        }
    }

    public String extractPath(String pathTarget, spark.Request req) {
        var path = new StringBuilder().append(pathTarget).append(req.pathInfo());

        Optional.ofNullable(req.queryString()).ifPresent(query -> {
            path.append("?");
            path.append(query);
        });

        return path.toString();
    }

    public Map<String, String> extractHeaders(spark.Request req) {
        Map<String, String> mapHeaders = new HashMap<>();
        for (String header : req.headers()) {
            if (!header.equals("Content-Length")) mapHeaders.put(header, req.headers(header));
        }
        return mapHeaders;
    }

    public byte[] extractBody(spark.Request req) {
        return req.bodyAsBytes();
    }

}
