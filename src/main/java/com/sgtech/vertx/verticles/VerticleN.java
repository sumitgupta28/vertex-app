package com.sgtech.vertx.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VerticleN extends AbstractVerticle {

    public static void main(String[] args) {
        final var vertex = Vertx.vertx();
        vertex.deployVerticle(new VerticleN());
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        log.debug(" Start - {} ThreadName - {} , Config - {}",getClass().getName(),Thread.currentThread().getName(),config().toString());
        startPromise.complete();
    }
}
