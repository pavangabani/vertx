package org.example.promisefuture;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class Consumer extends AbstractVerticle {

    @Override
    public void start()
    {
        vertx.eventBus().consumer("topic",message ->
        {
            System.out.println(Thread.currentThread().getName() + " Consumer " + message.body());

            System.out.println("Before");

            executeTask(3).onComplete(stringAsyncResult ->
            {
                System.out.println(stringAsyncResult.result());
            });

//            executeTask(); //Wrong use case

            System.out.println("After");


        });

    }

    public static Future<String> executeTask()
    {
        Promise<String> promise = Promise.promise();

        Long i = 0L;

        System.out.println("I am in execute metrhod");

        //code use cpu cycle
        promise.complete();

        return promise.future();
    }

    public static Future<String> executeTask(int in)
    {
        Promise promise = Promise.<String>promise();

//        proceess

        Vertx.vertx().executeBlocking(()->
        {

            System.out.println("I am in execute metrhod " + in);

            try
            {
                Thread.sleep(10000);
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }

            return "Pavan";

        },false,asyncResult -> {

            if(asyncResult.succeeded())
            {
                promise.complete(asyncResult.result());
            }
            else
            {
                promise.fail("Fail");
            }

        });

        return promise.future();
    }

}
