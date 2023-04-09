package com.sgtech.vertx.verticles.worker;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WorkerExample extends AbstractVerticle {

    public static void main(String[] args) {
        final var vertex = Vertx.vertx();
        vertex.deployVerticle(new WorkerExample());
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {


        log.debug(" Start - {}", getClass().getName());
        vertx.deployVerticle(new WokerVerticle(),
                new DeploymentOptions()
                        .setWorker(true)
                        .setWorkerPoolSize(1)
                        .setWorkerPoolName("My-Worker-Vertical"));
        startPromise.complete();
        executeBlock();
    }

    private void executeBlock() {
        vertx.executeBlocking(event -> {
            log.debug(" Running Blocking");
            try {
                Thread.sleep(1000);
                event.complete();
                //event.fail(" Forced Failed");
            } catch (InterruptedException e) {
                log.error(" Failed ", e);
                event.fail(e);
            }
        }, result -> {
            if (result.succeeded()) {
                log.debug("  Blocking Calls Done");
            } else {
                log.debug("  Blocking Calls Failed due to {} ", result.cause());
            }
        });
    }
}
