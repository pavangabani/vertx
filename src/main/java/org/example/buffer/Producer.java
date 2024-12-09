package org.example.buffer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.concurrent.atomic.AtomicInteger;

public class Producer extends AbstractVerticle {

    @Override
    public void start()
    {
        vertx.setPeriodic(1000, timerId -> {
            Buffer buffer = Buffer.buffer();
            int messageId = 1;

            // Write an integer (Message ID) and a UTF-8 string (Content) to the buffer
            buffer.appendInt(messageId);                 // 4 bytes for Message ID
            buffer.appendString("Hello, Consumer!");     // UTF-8 encoded string for Content

            // Send the buffer to the EventBus
            vertx.eventBus().send("buffer.address", buffer);
            System.out.println("Producer sent: Message ID -> " + messageId + ", Content -> Hello, Consumer!");

        });
    }
}
