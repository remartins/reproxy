package com.github.remartins.reproxy.orchestrator;

import java.io.IOException;

@FunctionalInterface
public interface IOrchestrator<T, R> {

	R apply(T t) throws IOException;
}
