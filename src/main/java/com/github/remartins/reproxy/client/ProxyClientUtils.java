package com.github.remartins.reproxy.client;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProxyClientUtils {

    public InputStream updateResponseServer(HttpResponse responseClient, OutputStream osServer) throws IOException {
        HttpEntity entity = responseClient.getEntity();
        if (entity == null) return null;

        entity.writeTo(osServer);

        return entity.getContent();
    }

    public void updateHeadersWithRequest(Map<String, String> headers, Request requestClient) {
        for (String header : headers.keySet()) {
            if (!header.equals("Content-Length")) requestClient.setHeader(header, headers.get(header));
        }
    }

    public void updateBodyWithRequest(Request request, byte[] body) {
        Optional.ofNullable(body).ifPresent(request::bodyByteArray);
    }

    public Map<String, String> extractHeaders(HttpResponse response) {
        Map<String, String> headers = new HashMap<>();
        for (Header header : response.getAllHeaders()) {
            headers.put(header.getName(), header.getValue());
        }
        return headers;
    }
}
