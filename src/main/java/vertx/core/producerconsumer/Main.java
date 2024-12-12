package vertx.core.producerconsumer;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.ThreadingModel;
import io.vertx.core.Vertx;

public class Main {
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx(); // Create a Vert.x instance

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

        Vertx vertx1 = Vertx.vertx(); // Create a Vert.x instance

        System.out.println(vertx1 +""+ vertx);

        vertx1.deployVerticle(Producer1.class, deploymentOptions, res ->
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

    }
}