package com.github.remartins.reproxy.server;

import com.github.remartins.reproxy.dto.RequestOrchDTO;
import com.github.remartins.reproxy.dto.ResponseOrchDTO;
import com.github.remartins.reproxy.orchestrator.HTTPType;
import com.github.remartins.reproxy.orchestrator.IOrchestrator;
import spark.Service;

import static com.github.remartins.reproxy.exceptions.CatchException.catchException;

public class ProxyServer implements IServer {

    private static final String PATH_SERVER = "/*";
    private Service service;
    private final int port;
    private final String pathTarget;
    private final ProxyServerUtils utils;

    private IOrchestrator<RequestOrchDTO, ResponseOrchDTO> orchestrate;

    public ProxyServer(int port, String pathTarget, ProxyServerUtils utils) {
        this.port = port;
        this.pathTarget = pathTarget;
        this.utils = utils;
    }

    public void start(IOrchestrator<RequestOrchDTO, ResponseOrchDTO> orchestrate) {
        this.orchestrate = orchestrate;

        this.service = Service.ignite();
        this.service.port(this.port);

        this.routeGet(this.pathTarget);
        this.routeHead(this.pathTarget);
        this.routeOptions(this.pathTarget);
        this.routePost(this.pathTarget);
        this.routePut(this.pathTarget);
        this.routePatch(this.pathTarget);
        this.routeDelete(this.pathTarget);
    }

    private void routeGet(String pathTarget) {
        this.service.get(PATH_SERVER, (req, res) -> catchException(() -> {

            var path = utils.extractPath(pathTarget, req);
            var headers = utils.extractHeaders(req);
            var os = res.raw().getOutputStream();

            var request = new RequestOrchDTO(HTTPType.GET, path, headers, os, null);

            ResponseOrchDTO responseClient = this.orchestrate.apply(request);

            utils.updateHeadersWithResponse(res, responseClient.headers());
            utils.updateStatusWithResponse(res, responseClient.statusCode());

            return res.raw();
        }));
    }

    private void routeHead(String pathTarget) {
        this.service.head(PATH_SERVER, (req, res) -> catchException(() -> {

            var path = utils.extractPath(pathTarget, req);
            var headers = utils.extractHeaders(req);
            var os = res.raw().getOutputStream();

            var request = new RequestOrchDTO(HTTPType.HEAD, path, headers, os, null);

            ResponseOrchDTO responseClient = this.orchestrate.apply(request);

            utils.updateHeadersWithResponse(res, responseClient.headers());
            utils.updateStatusWithResponse(res, responseClient.statusCode());

            return res.raw();
        }));
    }

    private void routeOptions(String pathTarget) {
        this.service.options(PATH_SERVER, (req, res) -> catchException(() -> {

            var path = utils.extractPath(pathTarget, req);
            var headers = utils.extractHeaders(req);
            var os = res.raw().getOutputStream();

            var request = new RequestOrchDTO(HTTPType.OPTIONS, path, headers, os, null);

            ResponseOrchDTO responseClient = this.orchestrate.apply(request);

            utils.updateHeadersWithResponse(res, responseClient.headers());
            utils.updateStatusWithResponse(res, responseClient.statusCode());

            return res.raw();
        }));
    }

    private void routePost(String pathTarget) {
        this.service.post(PATH_SERVER, (req, res) -> catchException(() -> {

            var path = utils.extractPath(pathTarget, req);
            var headers = utils.extractHeaders(req);
            var os = res.raw().getOutputStream();
            var body = utils.extractBody(req);

            var request = new RequestOrchDTO(HTTPType.POST, path, headers, os, body);

            ResponseOrchDTO responseClient = this.orchestrate.apply(request);

            utils.updateHeadersWithResponse(res, responseClient.headers());
            utils.updateStatusWithResponse(res, responseClient.statusCode());

            return res.raw();
        }));
    }


    private void routePut(String pathTarget) {
        this.service.put(PATH_SERVER, (req, res) -> catchException(() -> {

            var path = utils.extractPath(pathTarget, req);
            var headers = utils.extractHeaders(req);
            var os = res.raw().getOutputStream();
            var body = utils.extractBody(req);

            var request = new RequestOrchDTO(HTTPType.PUT, path, headers, os, body);

            ResponseOrchDTO responseClient = this.orchestrate.apply(request);

            utils.updateHeadersWithResponse(res, responseClient.headers());
            utils.updateStatusWithResponse(res, responseClient.statusCode());

            return res.raw();
        }));
    }


    private void routePatch(String pathTarget) {
        this.service.patch(PATH_SERVER, (req, res) -> catchException(() -> {

            var path = utils.extractPath(pathTarget, req);
            var headers = utils.extractHeaders(req);
            var os = res.raw().getOutputStream();
            var body = utils.extractBody(req);

            var request = new RequestOrchDTO(HTTPType.PATCH, path, headers, os, body);

            ResponseOrchDTO responseClient = this.orchestrate.apply(request);

            utils.updateHeadersWithResponse(res, responseClient.headers());
            utils.updateStatusWithResponse(res, responseClient.statusCode());

            return res.raw();
        }));
    }

    private void routeDelete(String pathTarget) {
        this.service.delete(PATH_SERVER, (req, res) -> catchException(() -> {

            var path = utils.extractPath(pathTarget, req);
            var headers = utils.extractHeaders(req);
            var os = res.raw().getOutputStream();
            var body = utils.extractBody(req);

            var request = new RequestOrchDTO(HTTPType.DELETE, path, headers, os, body);

            ResponseOrchDTO responseClient = this.orchestrate.apply(request);

            utils.updateHeadersWithResponse(res, responseClient.headers());
            utils.updateStatusWithResponse(res, responseClient.statusCode());

            return res.raw();
        }));
    }
}
