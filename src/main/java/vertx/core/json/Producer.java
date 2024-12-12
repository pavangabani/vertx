package vertx.core.json;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.concurrent.atomic.AtomicInteger;

public class Producer extends AbstractVerticle {

    @Override
    public void start()
    {
        AtomicInteger i = new AtomicInteger();

        vertx.setPeriodic(1000, id->
        {
            String msg = "message " + i;

            JsonObject jsonObject = new JsonObject();

            jsonObject.put("message", msg);

            JsonArray jsonArray = new JsonArray().add("pavan").add("smit");

            jsonObject.put("json_arrray",jsonArray);

            System.out.println(Thread.currentThread().getName() + " Produce " + msg);

            vertx.eventBus().send("topic", jsonObject);

            i.getAndIncrement();
        });
    }
}
