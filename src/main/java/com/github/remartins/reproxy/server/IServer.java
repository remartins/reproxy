package com.github.remartins.reproxy.server;

import com.github.remartins.reproxy.dto.RequestOrchDTO;
import com.github.remartins.reproxy.dto.ResponseOrchDTO;
import com.github.remartins.reproxy.orchestrator.IOrchestrator;

public interface IServer {

    void start(IOrchestrator<RequestOrchDTO, ResponseOrchDTO> orchestrate);

}
