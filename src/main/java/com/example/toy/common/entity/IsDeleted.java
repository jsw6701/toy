package com.example.toy.common.entity;

import com.example.toy.common.domain.IIsDeleted;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

@MappedSuperclass
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class IsDeleted implements IIsDeleted<String> {

  @ColumnDefault("'N'")
  @Column(name = "IS_DELETED", length = 1)
  private String isDeleted;
}
