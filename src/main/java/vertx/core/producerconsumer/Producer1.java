package vertx.core.producerconsumer;

import io.vertx.core.AbstractVerticle;

import java.util.concurrent.atomic.AtomicInteger;

public class Producer1 extends AbstractVerticle {

    @Override
    public void start()
    {
        AtomicInteger i = new AtomicInteger();

        vertx.setPeriodic(1000, id->
        {
            String msg = "message from vertx1 :- " + i;

            System.out.println(Thread.currentThread().getName() + " Produce " + msg);

            vertx.eventBus().send("topic",msg );

            i.getAndIncrement();
        });
    }
}
