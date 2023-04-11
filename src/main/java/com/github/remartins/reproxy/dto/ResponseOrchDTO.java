package com.github.remartins.reproxy.dto;

import java.io.InputStream;
import java.util.Map;

public record ResponseOrchDTO(int statusCode, Map<String, String> headers, InputStream body) {


}
