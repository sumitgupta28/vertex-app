package com.sgtech.vertx;


import io.netty.handler.codec.http.HttpHeaderNames;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class MainVerticle extends AbstractVerticle {

	public static void main(String[] args) {
		var vertex = Vertx.vertx();
		vertex.deployVerticle(new MainVerticle());

	}

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		vertx.createHttpServer().requestHandler(
				req -> {
					req.response().
							putHeader(HttpHeaderNames.CONTENT_TYPE.toString(),"text/plain")
							.end("Hello From Verticle");
				}
		).listen(8888, http -> {
			if(http.succeeded()){
				startPromise.complete();
				log.debug(" Http Server Started");
			} else {
				startPromise.fail(http.cause());
			}
		});
	}
}
