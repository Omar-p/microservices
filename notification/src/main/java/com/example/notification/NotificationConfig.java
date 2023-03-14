package com.example.notification;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {
  @Value("${rabbitmq.exchanges.internal")
  private String internalExchange;

  @Value("${rabbitmq.queues.notification}")
  private String notificationQueue;

  @Value("${rabbitmq.routing-keys.notification}")
  private String internalRoutingKey;

  public String getInternalExchange() {
    return internalExchange;
  }

  public String getNotificationQueue() {
    return notificationQueue;
  }

  public String getInternalRoutingKey() {
    return internalRoutingKey;
  }

  @Bean
  TopicExchange internalExchange() {
    return new TopicExchange(internalExchange);
  }

  @Bean
  Queue notificationQueue() {
    return new Queue(notificationQueue);
  }

  @Bean
  Binding internalToNotificationBinding() {
    return BindingBuilder
        .bind(notificationQueue())
        .to(internalExchange())
        .with(internalRoutingKey);
  }


}
