package com.github.remartins.reproxy.log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public interface ILogger {

    void logStart(String httpType);

    void logRequest(String path);

    void logResponse(String path);

    void logHttpStatusResponse(int statusCode);

    void logHeadersRequest(Map<String, String> headers);

    void logHeadersResponse(Map<String, String> headers);

    void logBodyRequest(InputStream is) throws IOException;

    void logBodyResponse(InputStream is) throws IOException;

    void flush();

}
