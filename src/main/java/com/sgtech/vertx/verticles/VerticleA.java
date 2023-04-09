package com.sgtech.vertx.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VerticleA extends AbstractVerticle {

    public static void main(String[] args) {
        final var vertex = Vertx.vertx();
        vertex.deployVerticle(new VerticleA());
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        log.debug(" Start - {}",getClass().getName());
        vertx.deployVerticle(new VerticleAA(), whenUnDeployed -> {

            vertx.undeploy(whenUnDeployed.result());
            log.debug(" Undeploy verticleId {}- {}",whenUnDeployed.result(),getClass().getName());

        });
        vertx.deployVerticle(new VerticleAB() , whenUnDeployed -> {
            log.debug(" Undeploy verticleId {}- {}",whenUnDeployed.result(),getClass().getName());

        });
        startPromise.complete();
    }
}
