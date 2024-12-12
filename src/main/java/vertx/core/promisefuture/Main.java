package vertx.core.promisefuture;

import io.vertx.core.*;


public class Main
{
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