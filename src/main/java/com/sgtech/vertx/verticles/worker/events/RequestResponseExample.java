package com.sgtech.vertx.verticles.worker.events;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestResponseExample {


    public static final String REQUEST_ADDRESS = "my.request.address";

    public static void main(String[] args) {
        var vertx = Vertx.vertx();

        vertx.deployVerticle(new RequestVerticle());
        vertx.deployVerticle(new ResponseVerticle());
    }

    static class RequestVerticle extends AbstractVerticle {

        @Override
        public void start(Promise<Void> startPromise) throws Exception {
            log.debug(" Start - {}", getClass().getName());
            startPromise.complete();
            var eventBus = vertx.eventBus();
            log.debug(" Sending Messages.... ");
            eventBus.<String>request(REQUEST_ADDRESS, "Hello World", replyHandler -> {
                log.debug("Received Response {} ", replyHandler.result().body());
            });
        }
    }


    static class ResponseVerticle extends AbstractVerticle {
        @Override
        public void start(Promise<Void> startPromise) throws Exception {
            log.debug(" Start - {}", getClass().getName());
            startPromise.complete();
            var eventBus = vertx.eventBus().<String>consumer(REQUEST_ADDRESS, handler -> {
                log.debug(" Received Messages.... {}", handler.body());
                handler.reply(" Received Your Message Thanks !! ");
            });

        }
    }
}
