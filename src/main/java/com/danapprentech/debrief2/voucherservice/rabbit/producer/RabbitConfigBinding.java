package com.danapprentech.debrief2.voucherservice.rabbit.producer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class RabbitConfigBinding
{
    @Autowired
    private AmqpAdmin amqpAdmin;

    @Value("${spring.rabbitmq.direct.queue}")
    private String cobaQueue;

    @Value("${spring.rabbitmq.direct.exchange}")
    private String directExchange;

    @Value("${spring.rabbitmq.direct.binding}")
    private String cobaBinding;

    @Value("${spring.rabbitmq.direct.routingkey}")
    private String routingkeyDirect;

    @Bean
    Queue cobaQueue()
    {
        return new Queue(cobaQueue,true,false,false);
    }

    @Bean
    DirectExchange cobaExchange()
    {
        return new DirectExchange(directExchange,true,false);
    }

    @Bean
    Binding cobaBinding()
    {
        return BindingBuilder.bind(cobaQueue()).to(cobaExchange()).with(routingkeyDirect);
    }

    @Bean
    AmqpTemplate directExchange(ConnectionFactory connectionFactory, MessageConverter messageConverter)
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.setExchange(directExchange);
        return rabbitTemplate;
    }

    @PostConstruct
    void init()
    {
        amqpAdmin.declareQueue(cobaQueue());
        amqpAdmin.declareExchange(cobaExchange());
        amqpAdmin.declareBinding(cobaBinding());
    }

}

