package com.example.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.nio.charset.StandardCharsets;

/*
 메시지 송수신을 위한 큐 생성
 TestQ라는 큐를 생성
 */
public class Sender {

    private final static String QUEUE_NAME="hello";

    public static void main(String[] argv)throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try(Connection connection = factory.newConnection();
            Channel channel= connection.createChannel()){
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME,null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + " '");
        }
    }

}
