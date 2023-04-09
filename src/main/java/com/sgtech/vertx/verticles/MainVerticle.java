package com.sgtech.vertx.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class MainVerticle extends AbstractVerticle {

    public static void main(String[] args) {
        final var vertx = Vertx.vertx();
        vertx.deployVerticle(new MainVerticle());

    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        log.debug(" Start - {}",getClass().getName());
        vertx.deployVerticle(new VerticleA());
        vertx.deployVerticle(new VerticleB());
        vertx.deployVerticle(VerticleN.class,
                new DeploymentOptions()
                        .setInstances(4)
                        .setConfig(new JsonObject()
                                .put("id", UUID.randomUUID().toString())
                                .put("name",VerticleN.class.getSimpleName()))
        );

        startPromise.complete();
    }
}
