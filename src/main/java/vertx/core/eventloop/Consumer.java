package vertx.core.eventloop;

import io.vertx.core.AbstractVerticle;

public class Consumer extends AbstractVerticle {

    @Override
    public void start()
    {
        vertx.eventBus().consumer("topic",message ->
        {

        });

        System.out.println(Thread.currentThread().getName() + " Consumer Main");
    }

}
