package com.tienngv.security.rabbit;

import com.tienngv.security.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class MessageConsumer {

    @RabbitListener(queues = RabbitConfig.TXT_QUEUE)
    public void receive(String message) {
        System.out.println(LocalDateTime.now() + "Mess: " + message);
        if (message.contains("ERROR")) {
            // Giả lập lỗi → message sẽ bị đẩy sang DLQ
            throw new RuntimeException("❌ Lỗi xử lý message");
        }
        System.out.println("====================");
    }

    @RabbitListener(queues = RabbitConfig.DLQ_QUEUE)
    public void handleDLQ(String message) {
        try {
            System.out.println("⚠️ Message bị lỗi đẩy sang DLQ: " + message);
            // Xử lý/log/save message lỗi tại đây
        } catch (Exception e) {
            // ❌ Không nên throw tiếp, tránh loop
            System.err.println("❌ Lỗi khi xử lý message trong DLQ: " + e.getMessage());
        }
    }

    @RabbitListener(queues = RabbitConfig.USER_QUEUE)
    public void receive(User user) {
        System.out.println(LocalDateTime.now() + " Nhận user: " + user.getFullName() + " - Email: " + user.getEmail());
        try {
            Thread.sleep(300);
        } catch (InterruptedException ignored) {
        }
    }

    @RabbitListener(queues = RabbitConfig.ORDER_QUEUE)
    public void receiveOrder(String msg) {
        System.out.println(LocalDateTime.now() + " Nhận {ORDER} {CREATE}: " + msg);
        try {
            Thread.sleep(300);
        } catch (InterruptedException ignored) {
        }
    }

    @RabbitListener(queues = RabbitConfig.MAIL_QUEUE)
    public void receiveMail(String msg) {
        System.out.println(LocalDateTime.now() + " Nhận MAIL: " + msg);
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
    }

    @RabbitListener(queues = RabbitConfig.ORDER_UPDATE_QUEUE)
    public void receiveOrderU(String msg) {
        System.out.println(LocalDateTime.now() + " Nhận ORDER UPDATE: " + msg);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }



}

