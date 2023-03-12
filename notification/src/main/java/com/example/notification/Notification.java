package com.example.notification;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Notification {

  @Id
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "notification_id_sequence"
  )
  @SequenceGenerator(
      name = "notification_id_sequence",
      sequenceName = "notification_id_sequence",
      allocationSize = 1
  )
  private Integer notificationId;

  private String message;

  private String sender;


  private String toCustomerEmail;

  private Integer toCustomerId;
  private LocalDateTime sentAt;
}
