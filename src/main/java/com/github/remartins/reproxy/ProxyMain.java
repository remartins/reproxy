package com.github.remartins.reproxy;

import com.github.remartins.reproxy.client.ProxyClient;
import com.github.remartins.reproxy.client.ProxyClientUtils;
import com.github.remartins.reproxy.log.ProxyLogger;
import com.github.remartins.reproxy.orchestrator.ProxyOrchestrator;
import com.github.remartins.reproxy.server.ProxyServer;
import com.github.remartins.reproxy.server.ProxyServerUtils;


public class ProxyMain {

    public static void main(String[] args) {


        var port = 8080;
        var path = "http://127.0.0.1:9090";

        new ProxyOrchestrator(new ProxyLogger(), new ProxyClient(new ProxyClientUtils()), new ProxyServer(port, path, new ProxyServerUtils())).start();
    }

}
