package com.example.toy.common.entity;

import com.example.toy.common.domain.ICreationAudit;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

@MappedSuperclass
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class CreationAudit implements ICreationAudit<LocalDateTime> {
  @CreatedBy
  @Column(name = "CREATOR_CD", length = 50)
  private String creatorCd;

  @CreatedDate
  @Column(name = "CREATED_AT")
  private LocalDateTime createdAt;
}
