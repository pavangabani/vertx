package vertx.core.json;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;


public class Main {
    public static void main(String[] args)
    {
        VertxOptions options = new VertxOptions().setEventLoopPoolSize(1);

        Vertx vertx = Vertx.vertx(options); // Create a Vert.x instance

        DeploymentOptions deploymentOptions= new DeploymentOptions();

        deploymentOptions.setInstances(1);

        vertx.deployVerticle(Consumer.class, deploymentOptions);

        vertx.deployVerticle(Producer.class, deploymentOptions);
    }
}