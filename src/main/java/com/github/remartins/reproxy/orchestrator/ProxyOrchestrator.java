package com.github.remartins.reproxy.orchestrator;

import com.github.remartins.reproxy.client.IClient;
import com.github.remartins.reproxy.log.ILogger;
import com.github.remartins.reproxy.server.IServer;

public class ProxyOrchestrator {

    private final ILogger logger;
    private final IClient client;
    private final IServer server;

    public ProxyOrchestrator(ILogger logger, IClient client, IServer server) {
        this.logger = logger;
        this.client = client;
        this.server = server;
    }

    public void start() {
        this.server.start((request) -> request.type().execute(logger, client, request));
    }

}
