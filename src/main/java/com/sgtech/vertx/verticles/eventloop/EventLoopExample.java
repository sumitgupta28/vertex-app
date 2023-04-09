package com.sgtech.vertx.verticles.eventloop;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class EventLoopExample extends AbstractVerticle {

    public static void main(String[] args) {
        final var vertx = Vertx.vertx(
                new VertxOptions()
                        .setMaxEventLoopExecuteTime(500)
                        .setMaxEventLoopExecuteTimeUnit(TimeUnit.MILLISECONDS)
                        .setBlockedThreadCheckInterval(1)
                        .setBlockedThreadCheckIntervalUnit(TimeUnit.SECONDS)
                        .setEventLoopPoolSize(2)

        );
        vertx.deployVerticle(new EventLoopExample(),new DeploymentOptions().setInstances(4));
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        log.debug(" Start - {}",getClass().getName());
        startPromise.complete();
        //Thread.sleep(5000);
    }
}
