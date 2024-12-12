package vertx.core.first;

import io.vertx.core.AbstractVerticle;

public class HelloWorldVerticle extends AbstractVerticle {

    @Override
    public void start() {
        vertx.createHttpServer()
                .requestHandler(req -> req.response().end("Hello, World!"))
                .listen(8080, http -> {
                    if (http.succeeded()) {
                        System.out.println("HTTP server started on port 8080");
                    } else {
                        System.out.println("Failed to start HTTP server: " + http.cause().getMessage());
                    }
                });
    }
}