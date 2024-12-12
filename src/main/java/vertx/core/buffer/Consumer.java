package vertx.core.buffer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;

public class Consumer extends AbstractVerticle {

    @Override
    public void start()
    {
        vertx.eventBus().consumer("buffer.address", message -> {
            Buffer buffer = (Buffer) message.body();

            byte[] rawBytes = buffer.getBytes();
            System.out.println("Consumer received (raw bytes): " + new String(rawBytes));

            String fullMessage = buffer.toString();
            System.out.println("Consumer received (full message): " + fullMessage);

            if (buffer.length() >= 4) { // Ensure there's enough data for an int and string
                int messageId = buffer.getInt(0);                    // First 4 bytes
                String content = buffer.getString(4, buffer.length()); // Rest of the message
                System.out.println("Consumer processed: Message ID -> " + messageId + ", Content -> " + content);
            }

        });
    }

}
