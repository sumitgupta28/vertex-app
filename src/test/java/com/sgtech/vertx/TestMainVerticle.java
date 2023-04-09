package com.sgtech.vertx;

import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(VertxExtension.class)
public class TestMainVerticle {

    @BeforeEach
    void deployVerticle(Vertx vertx, VertxTestContext vertxTestContext){
        vertx.deployVerticle(new MainVerticle(),
                vertxTestContext.succeeding(
                        id -> vertxTestContext.completeNow()
                ));
    }

    @Test
    void verticleDeployed(Vertx vertx,VertxTestContext vertxTestContext){
        vertxTestContext.completeNow();
    }
}
