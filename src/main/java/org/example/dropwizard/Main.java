package org.example.dropwizard;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.ThreadingModel;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.dropwizard.DropwizardMetricsOptions;
import io.vertx.ext.dropwizard.MetricsService;

public class Main {
    public static void main(String[] args)
    {

        DropwizardMetricsOptions dropwizardMetricsOptions = new DropwizardMetricsOptions().setEnabled(true);

        Vertx vertx = Vertx.vertx(new VertxOptions().setMetricsOptions(dropwizardMetricsOptions)); // Create a Vert.x instance

        DeploymentOptions deploymentOptions= new DeploymentOptions();

        deploymentOptions.setInstances(1);

        vertx.deployVerticle(Producer.class, deploymentOptions, res ->
        {
            if (res.succeeded())
            {
                System.out.println("Producer Verticle deployed successfully!");
            }
            else
            {
                System.out.println("Verticle deployment failed: " + res.cause().getMessage());
            }
        });

        DeploymentOptions deploymentOptions1 = new DeploymentOptions();

        deploymentOptions1.setThreadingModel(ThreadingModel.WORKER).setWorkerPoolSize(4).setWorkerPoolName("temp");

        vertx.deployVerticle(Consumer.class,deploymentOptions1, res ->
        {
            if (res.succeeded())
            {
                System.out.println("Consumer Verticle deployed successfully!");
            }
            else
            {
                System.out.println("Verticle deployment failed: " + res.cause().getMessage());
            }
        });

        vertx.setPeriodic(5000, id -> {
            JsonObject metrics = MetricsService.create(vertx).getMetricsSnapshot("");


            System.out.println("Current Metrics Snapshot: " + metrics.encodePrettily());
        });

    }
}