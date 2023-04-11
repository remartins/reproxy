package com.github.remartins.reproxy.client;

import com.github.remartins.reproxy.dto.ResponseOrchDTO;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class ProxyClient implements IClient {

    private final ProxyClientUtils utils;

    public ProxyClient(ProxyClientUtils utils) {
        this.utils = utils;
    }

    public ResponseOrchDTO requestGet(String pathTarget, Map<String, String> headers, OutputStream osServer) throws IOException {
        Request request = Request.Get(pathTarget);

        utils.updateHeadersWithRequest(headers, request);

        HttpResponse response = request.execute().returnResponse();

        Map<String, String> headersExtract = utils.extractHeaders(response);
        int statusCode = response.getStatusLine().getStatusCode();

        var bodyResponse = utils.updateResponseServer(response, osServer);

        return new ResponseOrchDTO(statusCode, headersExtract, bodyResponse);
    }

    public ResponseOrchDTO requestHead(String pathTarget, Map<String, String> headers, OutputStream osServer) throws IOException {
        Request request = Request.Head(pathTarget);

        utils.updateHeadersWithRequest(headers, request);

        HttpResponse response = request.execute().returnResponse();

        Map<String, String> headersExtract = utils.extractHeaders(response);
        int statusCode = response.getStatusLine().getStatusCode();

        var bodyResponse = utils.updateResponseServer(response, osServer);

        return new ResponseOrchDTO(statusCode, headersExtract, bodyResponse);
    }

    public ResponseOrchDTO requestOptions(String pathTarget, Map<String, String> headers, OutputStream osServer) throws IOException {
        Request request = Request.Options(pathTarget);

        utils.updateHeadersWithRequest(headers, request);

        HttpResponse response = request.execute().returnResponse();

        Map<String, String> headersExtract = utils.extractHeaders(response);
        int statusCode = response.getStatusLine().getStatusCode();

        var bodyResponse = utils.updateResponseServer(response, osServer);

        return new ResponseOrchDTO(statusCode, headersExtract, bodyResponse);
    }

    public ResponseOrchDTO requestPost(String pathTarget, Map<String, String> headers, OutputStream osServer, byte[] body) throws IOException {
        Request request = Request.Post(pathTarget);

        utils.updateHeadersWithRequest(headers, request);
        utils.updateBodyWithRequest(request, body);

        HttpResponse response = request.execute().returnResponse();

        Map<String, String> headersExtract = utils.extractHeaders(response);
        int statusCode = response.getStatusLine().getStatusCode();

        var bodyResponse = utils.updateResponseServer(response, osServer);

        return new ResponseOrchDTO(statusCode, headersExtract, bodyResponse);
    }

    public ResponseOrchDTO requestPut(String pathTarget, Map<String, String> headers, OutputStream osServer, byte[] body) throws IOException {
        Request request = Request.Put(pathTarget);

        utils.updateHeadersWithRequest(headers, request);
        utils.updateBodyWithRequest(request, body);

        HttpResponse response = request.execute().returnResponse();

        Map<String, String> headersExtract = utils.extractHeaders(response);
        int statusCode = response.getStatusLine().getStatusCode();

        var bodyResponse = utils.updateResponseServer(response, osServer);

        return new ResponseOrchDTO(statusCode, headersExtract, bodyResponse);
    }


    public ResponseOrchDTO requestPatch(String pathTarget, Map<String, String> headers, OutputStream osServer, byte[] body) throws IOException {
        Request request = Request.Patch(pathTarget);

        utils.updateHeadersWithRequest(headers, request);
        utils.updateBodyWithRequest(request, body);

        HttpResponse response = request.execute().returnResponse();

        Map<String, String> headersExtract = utils.extractHeaders(response);
        int statusCode = response.getStatusLine().getStatusCode();

        var bodyResponse = utils.updateResponseServer(response, osServer);

        return new ResponseOrchDTO(statusCode, headersExtract, bodyResponse);
    }

    public ResponseOrchDTO requestDelete(String pathTarget, Map<String, String> headers, OutputStream osServer) throws IOException {
        Request request = Request.Delete(pathTarget);

        utils.updateHeadersWithRequest(headers, request);

        HttpResponse response = request.execute().returnResponse();

        Map<String, String> headersExtract = utils.extractHeaders(response);
        int statusCode = response.getStatusLine().getStatusCode();

        var bodyResponse = utils.updateResponseServer(response, osServer);

        return new ResponseOrchDTO(statusCode, headersExtract, bodyResponse);
    }
}
