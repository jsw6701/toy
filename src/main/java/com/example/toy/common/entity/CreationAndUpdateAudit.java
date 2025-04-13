package com.example.toy.common.entity;

import com.example.toy.common.domain.ICreationAudit;
import com.example.toy.common.domain.IUpdateAudit;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class CreationAndUpdateAudit implements ICreationAudit<LocalDateTime>, IUpdateAudit<LocalDateTime> {
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
}