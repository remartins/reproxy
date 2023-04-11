package com.github.remartins.reproxy.orchestrator;

import com.github.remartins.reproxy.client.IClient;
import com.github.remartins.reproxy.dto.RequestOrchDTO;
import com.github.remartins.reproxy.dto.ResponseOrchDTO;
import com.github.remartins.reproxy.log.ILogger;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public enum HTTPType {

    GET {
        @Override
        public ResponseOrchDTO execute(ILogger logger, IClient client, RequestOrchDTO request) throws IOException {

            logger.logStart(this.name());
            logger.logRequest(request.path());
            logger.logHeadersRequest(request.headers());

            ResponseOrchDTO response = client.requestGet(request.path(), request.headers(), request.os());

            logger.logResponse(request.path());
            logger.logHttpStatusResponse(response.statusCode());
            logger.logHeadersResponse(response.headers());
            logger.logBodyResponse(response.body());

            logger.flush();

            return response;
        }
    }, HEAD {
        @Override
        public ResponseOrchDTO execute(ILogger logger, IClient client, RequestOrchDTO request) throws IOException {

            logger.logStart(this.name());
            logger.logRequest(request.path());
            logger.logHeadersRequest(request.headers());

            ResponseOrchDTO response = client.requestHead(request.path(), request.headers(), request.os());

            logger.logResponse(request.path());
            logger.logHttpStatusResponse(response.statusCode());
            logger.logHeadersResponse(response.headers());
            logger.logBodyResponse(response.body());

            logger.flush();

            return response;
        }
    }, OPTIONS {
        @Override
        public ResponseOrchDTO execute(ILogger logger, IClient client, RequestOrchDTO request) throws IOException {

            logger.logStart(this.name());
            logger.logRequest(request.path());
            logger.logHeadersRequest(request.headers());

            ResponseOrchDTO response = client.requestOptions(request.path(), request.headers(), request.os());

            logger.logResponse(request.path());
            logger.logHttpStatusResponse(response.statusCode());
            logger.logHeadersResponse(response.headers());
            logger.logBodyResponse(response.body());

            logger.flush();

            return response;
        }
    }, POST {
        @Override
        public ResponseOrchDTO execute(ILogger logger, IClient client, RequestOrchDTO request) throws IOException {

            logger.logStart(this.name());
            logger.logRequest(request.path());
            logger.logHeadersRequest(request.headers());
            logger.logBodyRequest(new ByteArrayInputStream(request.body()));

            ResponseOrchDTO response = client.requestPost(request.path(), request.headers(), request.os(), request.body());

            logger.logResponse(request.path());
            logger.logHttpStatusResponse(response.statusCode());
            logger.logHeadersResponse(response.headers());
            logger.logBodyResponse(response.body());

            logger.flush();

            return response;
        }
    }, PUT {
        @Override
        public ResponseOrchDTO execute(ILogger logger, IClient client, RequestOrchDTO request) throws IOException {

            logger.logStart(this.name());
            logger.logRequest(request.path());
            logger.logHeadersRequest(request.headers());
            logger.logBodyRequest(new ByteArrayInputStream(request.body()));

            ResponseOrchDTO response = client.requestPut(request.path(), request.headers(), request.os(), request.body());

            logger.logResponse(request.path());
            logger.logHttpStatusResponse(response.statusCode());
            logger.logHeadersResponse(response.headers());
            logger.logBodyResponse(response.body());

            logger.flush();

            return response;
        }
    }, PATCH {
        @Override
        public ResponseOrchDTO execute(ILogger logger, IClient client, RequestOrchDTO request) throws IOException {

            logger.logStart(this.name());
            logger.logRequest(request.path());
            logger.logHeadersRequest(request.headers());
            logger.logBodyRequest(new ByteArrayInputStream(request.body()));

            ResponseOrchDTO response = client.requestPatch(request.path(), request.headers(), request.os(), request.body());

            logger.logResponse(request.path());
            logger.logHttpStatusResponse(response.statusCode());
            logger.logHeadersResponse(response.headers());
            logger.logBodyResponse(response.body());

            logger.flush();

            return response;
        }
    }, DELETE {
        @Override
        public ResponseOrchDTO execute(ILogger logger, IClient client, RequestOrchDTO request) throws IOException {

            logger.logStart(this.name());
            logger.logRequest(request.path());
            logger.logHeadersRequest(request.headers());

            ResponseOrchDTO response = client.requestDelete(request.path(), request.headers(), request.os());

            logger.logResponse(request.path());
            logger.logHttpStatusResponse(response.statusCode());
            logger.logHeadersResponse(response.headers());
            logger.logBodyResponse(response.body());

            logger.flush();

            return response;
        }
    };

    public abstract ResponseOrchDTO execute(ILogger logger, IClient client, RequestOrchDTO request) throws IOException;

}
