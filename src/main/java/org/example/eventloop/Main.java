package org.example.eventloop;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class Main {
    public static void main(String[] args)
    {
        VertxOptions options = new VertxOptions().setEventLoopPoolSize(16);

        Vertx vertx = Vertx.vertx(options); // Create a Vert.x instance

        DeploymentOptions deploymentOptions= new DeploymentOptions();

        deploymentOptions.setInstances(16);

        vertx.deployVerticle(Consumer.class, deploymentOptions);

        VertxOptions options1 = new VertxOptions().setEventLoopPoolSize(16);

        Vertx vertx1 = Vertx.vertx(options1); // Create a Vert.x instance

        DeploymentOptions deploymentOptions1= new DeploymentOptions();

        deploymentOptions1.setInstances(16);

        vertx1.deployVerticle(Consumer1.class, deploymentOptions1);
    }
}