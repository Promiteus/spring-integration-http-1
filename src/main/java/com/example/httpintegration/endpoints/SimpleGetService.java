package com.example.httpintegration.endpoints;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.util.LinkedMultiValueMap;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@MessageEndpoint
public class SimpleGetService {
    @ServiceActivator(inputChannel = "getInput", outputChannel = "getReply")
    public List<String> process(LinkedMultiValueMap payload) {
        log.info(String.format("a1: %s a2: %s", payload.get("a1").get(0).toString(), payload.get("a2").get(0).toString()));
        return List.of("1", "2", "3");
    }
}
