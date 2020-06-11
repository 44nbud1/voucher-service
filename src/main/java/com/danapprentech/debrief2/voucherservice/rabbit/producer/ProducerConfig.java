package com.danapprentech.debrief2.voucherservice.rabbit.producer;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.connection.AbstractConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ProducerConfig
{


    @Configuration
    public class RabbitConfigConnection
    {
        @Value("${spring.rabbitmq.username}")
        private String username;
        @Value("${spring.rabbitmq.port}")
        private int port;
        @Value("${spring.rabbitmq.password}")
        private String password;
        @Value("${spring.rabbitmq.virtualHost}")
        private String virtualHost;
        @Value("${spring.rabbitmq.host}")
        private String host;

        @Bean
        public ConnectionFactory connectionFactory()
        {
            AbstractConnectionFactory connectionFactory = new CachingConnectionFactory();
            connectionFactory.setUsername(username);
            connectionFactory.setPassword(password);
            connectionFactory.setPort(port);
            connectionFactory.setVirtualHost(virtualHost);
            connectionFactory.setHost(host);
            return connectionFactory;
        }

        @Bean
        public MessageConverter messageConverter()
        {
//        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
            return new Jackson2JsonMessageConverter();
        }

    }
}
