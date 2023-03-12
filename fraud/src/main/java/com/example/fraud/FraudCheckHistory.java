package com.example.fraud;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class FraudCheckHistory {

  @SequenceGenerator(
      name = "fraud_id_sequence",
      sequenceName = "fraud_id_sequence",
      allocationSize = 1
  )
  @GeneratedValue(
      generator = "fraud_id_sequence",
      strategy = GenerationType.SEQUENCE
  )
  @Id
  private Integer id;
  private Integer customerId;
  private Boolean isFraudster;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
