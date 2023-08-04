package com.example.httpintegration.endpoints;

import com.example.httpintegration.models.ToDo;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;


@MessageEndpoint
public class SimpleTransformer {
    @Transformer(inputChannel = "toTransform", outputChannel = "toLog")
    public ToDo process(ToDo message) {
         message.setDescription(message.getDescription().toUpperCase());
         return message;
    }
}
