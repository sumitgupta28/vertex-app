package com.sgtech.vertx.verticles.worker;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WokerVerticle extends AbstractVerticle {

    public static void main(String[] args) {
        final var vertex = Vertx.vertx();
        vertex.deployVerticle(new WokerVerticle());
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        log.debug(" Start - {}", getClass().getName());
        startPromise.complete();
        Thread.sleep(5000);
        log.debug(" Done - {}", getClass().getName());

    }
}
