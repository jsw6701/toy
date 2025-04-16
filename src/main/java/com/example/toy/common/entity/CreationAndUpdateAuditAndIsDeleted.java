package com.example.toy.common.entity;

import com.example.toy.common.domain.ICreationAudit;
import com.example.toy.common.domain.IIsDeleted;
import com.example.toy.common.domain.IUpdateAudit;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class CreationAndUpdateAuditAndIsDeleted
    implements ICreationAudit<LocalDateTime>, IUpdateAudit<LocalDateTime>, IIsDeleted<String> {
  @CreatedBy
  @Column(name = "CREATOR_CD", length = 50)
  private String creatorCd;

  @CreatedDate
  @Column(name = "CREATED_AT")
  private LocalDateTime createdAt;

  @LastModifiedBy
  @Column(name = "UPDATER_CD", length = 50)
  private String updaterCd;

  @LastModifiedDate
  @Column(name = "UPDATED_AT")
  private LocalDateTime updatedAt;

  @ColumnDefault("'N'")
  @Column(name = "IS_DELETED", length = 1)
  private String isDeleted;
}
