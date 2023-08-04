package com.example.httpintegration.endpoints;

import com.example.httpintegration.models.ToDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@MessageEndpoint
public class DiscardFilterService {
    @ServiceActivator(inputChannel = "discardChannel", outputChannel = "postReply")
    public ToDo process() {
        log.warn("Error!!!");
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Нет конента!");
    }
}
