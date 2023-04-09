package com.sgtech.vertx.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VerticleAB extends AbstractVerticle {

    public static void main(String[] args) {
        final var vertex = Vertx.vertx();
        vertex.deployVerticle(new VerticleAB());
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        log.debug(" Start - {}",getClass().getName());
        startPromise.complete();
    }

    @Override
    public void stop(Promise<Void> stopPromise) throws Exception {
        log.debug(" Stop - {}",getClass().getName());
        stopPromise.complete();
    }
}
