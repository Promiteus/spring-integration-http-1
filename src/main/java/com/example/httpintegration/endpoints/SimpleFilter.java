package com.example.httpintegration.endpoints;

import com.example.httpintegration.models.ToDo;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.MessageEndpoint;

@MessageEndpoint
public class SimpleFilter {
    @Filter(inputChannel = "postInput", outputChannel = "toTransform", discardChannel = "discardChannel")
    public boolean process(ToDo message) {
        return message.isCompleted();
    }
}
