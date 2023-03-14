package com.example.notification;

import com.example.clients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class NotificationService {

  private final NotificationRepository notificationRepository;


  public void send(NotificationRequest notificationRequest) {
    notificationRepository.save(Notification.builder()
        .message(notificationRequest.message())
        .sentAt(LocalDateTime.now())
        .sender("Omar")
        .toCustomerEmail(notificationRequest.toCustomerEmail())
        .toCustomerId(notificationRequest.toCustomerId())
        .build());
  }
}
