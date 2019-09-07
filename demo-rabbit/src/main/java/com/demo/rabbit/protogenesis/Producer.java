package com.demo.rabbit.protogenesis;

import com.rabbitmq.client.*;

public class Producer {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            String routingKey = getRouting(argv);
            String message = getMessage(argv);

            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");
        }
    }


//    private static final String EXCHANGE_NAME = "direct_logs";
//
//    public static void main(String[] argv) throws Exception {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        try (Connection connection = factory.newConnection();
//             Channel channel = connection.createChannel()) {
//            channel.exchangeDeclare(EXCHANGE_NAME, "direct");
//            channel.queueDeclare("hello", true, false, false, null);
//            channel.queueBind("hello", EXCHANGE_NAME, "ab");
//
//            String severity = "ab";
//            String message = "message0-00";
//
//            channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
//            System.out.println(" [x] Sent '" + severity + "':'" + message + "'");
//        }
//    }


//        private static final String EXCHANGE_NAME = "logs";
//
//        public static void main(String[] argv) throws Exception {
//            ConnectionFactory factory = new ConnectionFactory();
//            factory.setHost("localhost");
//            try (Connection connection = factory.newConnection();
//                 Channel channel = connection.createChannel()) {
//                channel.exchangeDeclare(EXCHANGE_NAME, "topic");
//
//                String message = argv.length < 1 ? "info: Hello World!" :
//                        String.join(" ", argv);
//
//                channel.basicPublish(EXCHANGE_NAME, "one", null, message.getBytes("UTF-8"));
//                System.out.println(" [x] Sent '" + message + "'");
//            }
//    }

//    private static final String TASK_QUEUE_NAME = "task_queue";
//
//    public static void main(String[] argv) throws Exception {
//
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        try (Connection connection = factory.newConnection();
//             Channel channel = connection.createChannel()) {
//            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
//
//            String message = String.join(" ", "hello");
//
//            channel.basicPublish("", TASK_QUEUE_NAME,
//                    MessageProperties.PERSISTENT_TEXT_PLAIN,
//                    message.getBytes("UTF-8"));
//            System.out.println(" [x] Sent '" + message + "'");
//        }
//    }

}
