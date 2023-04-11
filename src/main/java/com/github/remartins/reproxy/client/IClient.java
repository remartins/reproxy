package com.github.remartins.reproxy.client;

import com.github.remartins.reproxy.dto.ResponseOrchDTO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public interface IClient {

    ResponseOrchDTO requestGet(String pathTarget, Map<String, String> headers, OutputStream osServer) throws IOException;

    ResponseOrchDTO requestHead(String pathTarget, Map<String, String> headers, OutputStream osServer) throws IOException;

    ResponseOrchDTO requestOptions(String pathTarget, Map<String, String> headers, OutputStream osServer) throws IOException;

    ResponseOrchDTO requestPost(String pathTarget, Map<String, String> headers, OutputStream osServer, byte[] body) throws IOException;

    ResponseOrchDTO requestPut(String pathTarget, Map<String, String> headers, OutputStream osServer, byte[] body) throws IOException;

    ResponseOrchDTO requestPatch(String pathTarget, Map<String, String> headers, OutputStream osServer, byte[] body) throws IOException;

    ResponseOrchDTO requestDelete(String pathTarget, Map<String, String> headers, OutputStream osServer) throws IOException;

}
