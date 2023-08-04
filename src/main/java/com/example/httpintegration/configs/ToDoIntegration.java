package com.example.httpintegration.configs;

import com.example.httpintegration.models.ToDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpMethod;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.http.inbound.HttpRequestHandlingMessagingGateway;
import org.springframework.integration.http.inbound.RequestMapping;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.CrossOrigin;


@EnableIntegration
@Configuration
@Slf4j
public class ToDoIntegration {

    @Bean
    public MessageChannel postInput() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel getInput() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel postReply() {return new DirectChannel(); }

    @Bean
    public MessageChannel getReply() {return new DirectChannel(); }

    @Bean
    public MessageChannel discardChannel() {return new DirectChannel(); }

    @Bean
    public MessageChannel toTransform() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel toLog() {
        return new DirectChannel();
    }

    @Bean
    public RequestMapping post200Mapping() {
        RequestMapping requestMapping = new RequestMapping();
        requestMapping.setPathPatterns("/abs");
        requestMapping.setMethods(HttpMethod.POST);
        requestMapping.setProduces("application/json");
        return requestMapping;
    }

    @Bean
    public HttpRequestHandlingMessagingGateway postInbound() {
        HttpRequestHandlingMessagingGateway gateway =
                new HttpRequestHandlingMessagingGateway(true); //expectReply = false вернет ответ с кодом 200 мгновенно без ожидания выполнения процесса
        gateway.setRequestMapping(post200Mapping());
        gateway.setRequestPayloadType(ResolvableType.forClass(ToDo.class));
        gateway.setRequestChannelName("postInput");
        org.springframework.integration.http.inbound.CrossOrigin crossOrigin = new org.springframework.integration.http.inbound.CrossOrigin();
        crossOrigin.setOrigin("*");
        crossOrigin.setAllowedHeaders("*");
        gateway.setCrossOrigin(crossOrigin);
        /*reply - канал, куда нужно положить обработанный ответ. Ответ возвращает класс SimpleServiceActivator в outputChannel = "reply"*/
        gateway.setReplyChannel(postReply());
        return gateway;
    }

    @Bean
    public RequestMapping getMapping() {
        RequestMapping requestMapping = new RequestMapping();
        requestMapping.setPathPatterns("/abs");
        requestMapping.setParams("a1", "a2");
        requestMapping.setMethods(HttpMethod.GET);
        requestMapping.setProduces("application/json");
        return requestMapping;
    }

    @Bean
    public HttpRequestHandlingMessagingGateway getInbound() {
        HttpRequestHandlingMessagingGateway gateway =
                new HttpRequestHandlingMessagingGateway(true); //expectReply = false вернет ответ с кодом 200 мгновенно без ожидания выполнения процесса
        gateway.setRequestMapping(getMapping());
        gateway.setRequestChannelName("getInput");
        org.springframework.integration.http.inbound.CrossOrigin crossOrigin = new org.springframework.integration.http.inbound.CrossOrigin();
        crossOrigin.setOrigin("*");
        crossOrigin.setAllowedHeaders("*");
        gateway.setCrossOrigin(crossOrigin);
        /*reply - канал, куда нужно положить обработанный ответ. Ответ возвращает класс SimpleServiceActivator в outputChannel = "reply"*/
        gateway.setReplyChannel(getReply());
        return gateway;
    }
}
