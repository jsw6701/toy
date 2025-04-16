package com.example.toy.common.domain;

import java.time.temporal.Temporal;

public interface ICreationAudit<T extends Temporal> {
  String getCreatorCd();

  T getCreatedAt();
}
