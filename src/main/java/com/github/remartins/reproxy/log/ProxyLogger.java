package com.github.remartins.reproxy.log;

import org.fusesource.jansi.AnsiConsole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.ansi;

public class ProxyLogger implements ILogger {

    private final Logger log = LoggerFactory.getLogger(ProxyLogger.class);

    private final String VAL_REQUEST = "REQUEST";
    private final String VAL_RESPONSE = "RESPONSE";
    private final String VAL_HTTP_TYPE = "HTTP REQUEST TYPE";
    private final String VAL_BLOCK = "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++";
    private final String VAL_LINE = "-------------------------------------------------------------------------------------------------------------------------------------------------";
    private final String VAL_BREAK_LINE = "\n";

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private StringBuilder text;

    public ProxyLogger() {
        AnsiConsole.systemInstall();
    }

    public void logStart(String httpType) {
        this.text = new StringBuilder();
        this.text.append(VAL_BREAK_LINE);
        this.text.append(VAL_BREAK_LINE);

        this.text.append(ansi().fg(GREEN).a(simpleDateFormat.format(new Date())).a(" ").a(VAL_BLOCK).reset().toString());

        this.text.append(VAL_BREAK_LINE);
        this.text.append(VAL_BREAK_LINE);


        this.text.append(colored(VAL_HTTP_TYPE, httpType));
        this.text.append(VAL_BREAK_LINE);
        this.text.append(VAL_BREAK_LINE);
    }

    public void logRequest(String path) {
        this.logType(path, VAL_REQUEST);
    }

    public void logResponse(String path) {
        this.logType(path, VAL_RESPONSE);
    }

    private void logType(String pathTarget, String type) {
        this.text.append(colored(type, pathTarget));
        this.text.append(VAL_BREAK_LINE);
        this.text.append(VAL_BREAK_LINE);
    }

    public void logHttpStatusResponse(int statusCode) {

        this.text.append(colored(VAL_RESPONSE + " STATUS CODE: ", String.valueOf(statusCode)));
        this.text.append(VAL_BREAK_LINE);
        this.text.append(VAL_BREAK_LINE);
    }

    public void logHeadersRequest(Map<String, String> headers) {
        this.logHeaders(headers, VAL_REQUEST);
    }

    public void logHeadersResponse(Map<String, String> headers) {
        this.logHeaders(headers, VAL_RESPONSE);
    }

    private void logHeaders(Map<String, String> headers, String type) {
        this.text.append(colored(type + " HEADERS: "));
        this.text.append(VAL_BREAK_LINE);
        this.text.append(VAL_LINE);
        this.text.append(VAL_BREAK_LINE);

        for (var header : headers.entrySet()) {
            this.text.append(header.getKey()).append(" : ").append(header.getValue()).append(VAL_BREAK_LINE);
        }

        this.text.append(VAL_LINE);
        this.text.append(VAL_BREAK_LINE);
        this.text.append(VAL_BREAK_LINE);
    }

    public void logBodyRequest(InputStream is) throws IOException {
        if (Objects.nonNull(is)) {
            this.logBody(is, VAL_REQUEST);
        }
    }

    public void logBodyResponse(InputStream is) throws IOException {
        if (Objects.nonNull(is)) {
            this.logBody(is, VAL_RESPONSE);
        }
    }

    private void logBody(InputStream is, String type) throws IOException {
        if (Objects.nonNull(is)) {
            var byteArrayOutputStream = new ByteArrayOutputStream();
            is.transferTo(byteArrayOutputStream);

            String value = byteArrayOutputStream.toString(StandardCharsets.UTF_8);

            this.text.append(colored(type + " BODY: "));
            this.text.append(VAL_BREAK_LINE);
            this.text.append(VAL_LINE);
            this.text.append(VAL_BREAK_LINE);

            this.text.append(value);

            this.text.append(VAL_BREAK_LINE);
            this.text.append(VAL_LINE);
            this.text.append(VAL_BREAK_LINE);
            this.text.append(VAL_BREAK_LINE);
        }
    }

    public void flush() {
        this.text.append(VAL_BREAK_LINE);
        this.text.append(VAL_BREAK_LINE);
        this.text.append(VAL_BREAK_LINE);

        log.info(this.text.toString());
    }


    private String colored(String key) {
        return ansi().fg(GREEN).a(key).a(": ").reset().toString();
    }

    private String colored(String key, String value) {
        return ansi().fg(GREEN).a(key).a(": ").reset().a(value).toString();
    }
}
