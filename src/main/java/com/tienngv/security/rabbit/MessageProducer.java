//package com.tienngv.security.rabbit;
//
//import com.tienngv.security.entity.User;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MessageProducer {
//
//    private final RabbitTemplate rabbitTemplate;
//
//    public MessageProducer(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//    public void send(String message) {
//        rabbitTemplate.convertAndSend(
//                RabbitConfig.EXCHANGE_NAME,
//                RabbitConfig.ROUTING_KEY,
//                message
//        );
//        System.out.println("Sent: " + message);
//    }
//
//    public void sendUser(User user) {
//        rabbitTemplate.convertAndSend(
//                RabbitConfig.EXCHANGE_NAME,
//                RabbitConfig.ROUTING_KEY,
//                user
//        );
//        try{
//            System.out.println("U: " + user.getId());
//            Thread.sleep(3000);
//        }catch(InterruptedException e){
//            e.printStackTrace();
//        }
//        System.out.println("Sent: " + user.getId());
//    }
//}
//
