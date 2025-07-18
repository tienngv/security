package com.tienngv.security.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {
    // === Exchange & RoutingKey constants ===

    // Direct exchange
    public static final String USER_EXCHANGE = "jqk.user.exchange";
    public static final String TXT_EXCHANGE = "jqk.exchange";
    // Topic exchange
    public static final String TOPIC_EXCHANGE = "jqk.topic.exchange";
    // DLX_EXCHANGE
    public static final String DLX_EXCHANGE = "order.dlx.exchange";


    // Queue
    public static final String USER_QUEUE = "jqk.user.queue";
    public static final String TXT_QUEUE = "jqk.queue";
    public static final String ORDER_UPDATE_QUEUE = "jqk.order.update";
    public static final String ORDER_QUEUE = "jqk.order.create";
    public static final String MAIL_QUEUE = "jqk.order.mail.create";
    public static final String DLQ_QUEUE = "order.queue.dlq";


    // Routing Key
    //   * khớp 1 từ
    //   # khớp 0 hoặc nhiều từ
    public static final String USER_ROUTING_KEY = "jqk.user.#";
    public static final String ORDER_ROUTING_KEY = "jqk.order.#";
    public static final String UPDATE_ORDER_ROUTING_KEY = "jqk.order.update";
    public static final String PATTERN_CREATE_ROUTING = "jqk.order.#.create";
    public static final String JQK_ROUTING_KEY = "jqk.#";
    public static final String USER_ROUTING = "jqk.user.key";
    public static final String TXT_ROUTING = "jqk.key";
    public static final String DLQ_ROUTING_KEY = "order.routing.dlq";


    // === Common Queue (dùng chung giữa topic + direct) ===
    private Queue txtQueue() {
        return QueueBuilder.durable(TXT_QUEUE)
                .withArgument("x-dead-letter-exchange", DLX_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", DLQ_ROUTING_KEY)
                .build();
//        return new Queue(TXT_QUEUE, false).; // không durable
    }

    private Queue userQueue() {
        return new Queue(USER_QUEUE, false); // không durable
    }

    //     === Cấu hình Topic Exchange + Binding ===
    @Bean
    public Declarables topicBindings() {
        Queue orderCQueue = new Queue(ORDER_QUEUE, false);
        Queue orderUQueue = new Queue(ORDER_UPDATE_QUEUE, false);
        Queue mailQueue = new Queue(MAIL_QUEUE, false);
        Queue txtQueue = txtQueue(); // dùng chung cho topic & direct
        TopicExchange topicExchange = new TopicExchange(TOPIC_EXCHANGE);

        return new Declarables(
                orderCQueue,
                orderUQueue,
                mailQueue,
                topicExchange,
                BindingBuilder.bind(orderUQueue).to(topicExchange).with(UPDATE_ORDER_ROUTING_KEY),
                BindingBuilder.bind(txtQueue).to(topicExchange).with(PATTERN_CREATE_ROUTING),
                BindingBuilder.bind(orderCQueue).to(topicExchange).with(PATTERN_CREATE_ROUTING),
                BindingBuilder.bind(mailQueue).to(topicExchange).with(PATTERN_CREATE_ROUTING)
        );
    }

    // === Cấu hình Direct Exchange + Binding ===
    @Bean
    public Declarables directBindings() {
        DirectExchange userExchange = new DirectExchange(USER_EXCHANGE);
        DirectExchange txtExchange = new DirectExchange(TXT_EXCHANGE);
        DirectExchange dlxExchange = new DirectExchange(DLX_EXCHANGE);
        Queue txtQueue = txtQueue(); // dùng chung
        Queue userQueue = userQueue();
        Queue dlxQueue = QueueBuilder.durable(DLQ_QUEUE).build();

        return new Declarables(
                userExchange,
                txtExchange,
                dlxExchange,
                txtQueue,
                userQueue,
                dlxQueue,
                BindingBuilder.bind(userQueue).to(userExchange).with(USER_ROUTING),
                BindingBuilder.bind(txtQueue).to(txtExchange).with(TXT_ROUTING),
                BindingBuilder.bind(dlxQueue).to(dlxExchange).with(DLQ_ROUTING_KEY)
        );
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());

//        // Tự động gắn persistent cho tất cả message gửi đi
//        template.setMandatory(true);
//        template.setMessageConverter(new Jackson2JsonMessageConverter());
//        template.setBeforePublishPostProcessors(message -> {
//            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
//            return message;
//        });

        return template;
    }

    @Bean
    public MessageConverter messageConverter(DefaultClassMapper classMapper) {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        converter.setClassMapper(classMapper); // Gắn class mapper
        return converter;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            MessageConverter messageConverter
    ) {
        var factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);
        return factory;
    }

    @Bean
    public DefaultClassMapper classMapper() {
        DefaultClassMapper mapper = new DefaultClassMapper();

        // Ánh xạ __TypeId__ (string) → class thực tế
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("com.gpay.okila.request.User", com.tienngv.security.entity.User.class);

        mapper.setIdClassMapping(idClassMapping);
        return mapper;
    }

    public static void main(String[] args) {
        System.out.println("Nhap n: ");

    }

}



