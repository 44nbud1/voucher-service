package com.danapprentech.debrief2.voucherservice.rabbit.producer;

import com.danapprentech.debrief2.voucherservice.model.Voucher;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqProducer
{
    @Autowired
    AmqpTemplate directExchange;

    @Value("${spring.rabbitmq.direct.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.direct.routingkey}")
    private String routingKey;

    public void sendToRabbitVoucher(Voucher aan)
    {
        directExchange.convertAndSend(exchange,routingKey,aan);
    }

}
