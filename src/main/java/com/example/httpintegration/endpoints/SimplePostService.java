package com.example.httpintegration.endpoints;

import com.example.httpintegration.models.ToDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

@Slf4j
@MessageEndpoint
public class SimplePostService {
    @ServiceActivator(inputChannel = "toLog", outputChannel = "postReply")
    public ToDo process(ToDo message) {
        log.info(message.toString());
        return message;
    }
}
