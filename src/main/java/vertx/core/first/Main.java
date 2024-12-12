package vertx.core.first;

import io.vertx.core.Vertx;

public class Main {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx(); // Create a Vert.x instance
        vertx.deployVerticle(new HelloWorldVerticle(), res -> {
            if (res.succeeded()) {
                System.out.println("Verticle deployed successfully!");
            } else {
                System.out.println("Verticle deployment failed: " + res.cause().getMessage());
            }
        });

        vertx.deployVerticle("p");
    }
}