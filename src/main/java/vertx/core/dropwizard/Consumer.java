package vertx.core.dropwizard;

import io.vertx.core.AbstractVerticle;

public class Consumer extends AbstractVerticle {

    @Override
    public void start()
    {
        vertx.eventBus().consumer("topic",message ->
        {
//            System.out.println(Thread.currentThread().getName() + " Consumer1 " + message.body());

            try
            {
                Thread.sleep(10000);
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        });

        System.out.println(Thread.currentThread().getName() + " Main");

//        vertx.eventBus().consumer("topic",message ->
//        {
//            System.out.println(Thread.currentThread().getName() + " Consumer2 " + message.body());
//
////            try
////            {
////                Thread.sleep(10000);
////
////            }
////            catch (InterruptedException e)
////            {
////                throw new RuntimeException(e);
////            }
//        });
    }

}
