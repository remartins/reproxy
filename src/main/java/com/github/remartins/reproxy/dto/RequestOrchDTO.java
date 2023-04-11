package com.github.remartins.reproxy.dto;

import com.github.remartins.reproxy.orchestrator.HTTPType;

import javax.servlet.ServletOutputStream;
import java.util.Map;

public record RequestOrchDTO(HTTPType type, String path, Map<String, String> headers, ServletOutputStream os,
                             byte[] body) {


}
