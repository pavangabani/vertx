package org.example.filesystem;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;
import org.example.eventloop.Consumer;
import org.example.eventloop.Consumer1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class Main {
    public static void main(String[] args) throws IOException
    {
        Vertx vertx = Vertx.vertx();

        FileSystem fileSystem = vertx.fileSystem();

        String filePath  = "/home/pavan/Desktop/Motadata/Vertx/Vertx/src/main/java/org/example/filesystem/test.txt";

        Path path = Path.of(filePath);

        if(!Files.exists(path))
        {
            Files.createFile(path);
        }

        fileSystem.writeFile(filePath, Buffer.buffer("Hello Test!"), voidAsyncResult -> {
            if(voidAsyncResult.succeeded())
            {
                System.out.println("Write Successful");
            }
        });

        fileSystem.readFile(filePath, bufferAsyncResult -> {
            if(bufferAsyncResult.succeeded())
            {
                System.out.println("File Content:" + bufferAsyncResult.result().toString());
            }

        });

    }
}