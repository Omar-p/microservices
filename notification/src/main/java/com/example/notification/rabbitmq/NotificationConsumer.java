package com.example.notification.rabbitmq;

import com.example.clients.notification.NotificationRequest;
import com.example.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {


  private final NotificationService notificationService;

  @RabbitListener(queues = "${rabbitmq.queues.notification}")
  void sendNotification(NotificationRequest notificationRequest) {
    log.info("Received notification request: {}", notificationRequest);
    notificationService.send(notificationRequest);
    log.info("Notification sent: {}", notificationRequest);
  }

}
