package org.example.dropwizard;

import io.vertx.core.AbstractVerticle;

import java.util.concurrent.atomic.AtomicInteger;

public class Producer extends AbstractVerticle {

    @Override
    public void start()
    {
        AtomicInteger i = new AtomicInteger();

        vertx.setPeriodic(1000, id->
        {
            String msg = "message " + i;

//            System.out.println(Thread.currentThread().getName() + " Produce " + msg);

            vertx.eventBus().send("topic",msg );

            i.getAndIncrement();
        });
    }
}
