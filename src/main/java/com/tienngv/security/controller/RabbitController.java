//package com.tienngv.security.controller;
//
//import com.tienngv.security.entity.User;
//import com.tienngv.security.rabbit.MessageProducer;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/messages")
//public class RabbitController {
//
//    private final MessageProducer producer;
//
//    public RabbitController(MessageProducer producer) {
//        this.producer = producer;
//    }
//
//    @PostMapping
//    public ResponseEntity<String> sendMessage(@RequestBody String msg) {
//        producer.send(msg);
//        return ResponseEntity.ok("Message sent: " + msg);
//    }
//
//    @PostMapping("/user")
//    public ResponseEntity<String> sendMessageUser(@RequestBody List<User> msg) {
//        for (User user : msg) {
//            producer.sendUser(user);
//        }
//        return ResponseEntity.ok("OKE");
//    }
//}
//
