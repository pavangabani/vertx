package org.example.vertxconfig;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.*;
import io.vertx.core.json.JsonObject;


public class Main
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        ConfigStoreOptions jasonStore = new ConfigStoreOptions()
                .setType("file")
                .setConfig(new JsonObject().put("path","/home/pavan/Desktop/Motadata/Vertx/Vertx/src/main/java/org/example/vertxconfig/Config.json"));

        ConfigRetrieverOptions configRetrieverOptions = new ConfigRetrieverOptions().addStore(jasonStore);

        ConfigRetriever retriever = ConfigRetriever.create(vertx, configRetrieverOptions);

        retriever.getConfig(ar->{
            if(ar.succeeded())
            {
                JsonObject jsonObject = ar.result();

                System.out.println(jsonObject);
            }
        });
    }
}