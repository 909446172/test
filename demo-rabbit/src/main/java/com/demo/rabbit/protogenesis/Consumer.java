package com.demo.rabbit.protogenesis;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Consumer {


    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();

        String bindingKey = "ab";
            channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }

//    private static final String EXCHANGE_NAME = "direct_logs";
//
//    public static void main(String[] argv) throws Exception {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//
//        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
//        String queueName = channel.queueDeclare().getQueue();
//
//
//            String serverity = "ab";
//            channel.queueBind(queueName, EXCHANGE_NAME, serverity);
//        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
//
//        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//            String message = new String(delivery.getBody(), "UTF-8");
//            System.out.println(" [x] Received '" +
//                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
//        };
//        channel.basicConsume(queueName, false, deliverCallback, consumerTag -> { });
//    }


//    private static final String EXCHANGE_NAME = "logs";
//
//    public static void main(String[] argv) throws Exception {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//
//        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
//
//   //     String queueName = channel.queueDeclare().getQueue();
//            String   queueName = "one";
//        channel.queueBind(queueName, EXCHANGE_NAME, "one");
//
//        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
//
//        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//            String message = new String(delivery.getBody(), "UTF-8");
//            System.out.println(" [x] Received '" + message + "'");
//        };
//        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
//    }


//    private static final String TASK_QUEUE_NAME = "task_queue";
//
//    public static void main(String[] argv) throws Exception {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        final Connection connection = factory.newConnection();
//        final Channel channel = connection.createChannel();
//
//        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
//        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
//
//        channel.basicQos(1);
//
//        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//            String message = new String(delivery.getBody(), "UTF-8");
//            System.out.println(consumerTag);
//            System.out.println(" [x] Received '" + message + "'");
//            try {
//                doWork(message);
//            } finally {
//                System.out.println(" [x] Done");
//                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
//            }
//        };
//        channel.basicConsume(TASK_QUEUE_NAME, false, deliverCallback, consumerTag -> { });
//
//        System.out.println("程序已经走完!");
//    }
//
//    private static void doWork(String task) {
//        for (char ch : task.toCharArray()) {
//            if (ch == '.') {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException _ignored) {
//                    Thread.currentThread().interrupt();
//                }
//            }
//        }
//    }

}
