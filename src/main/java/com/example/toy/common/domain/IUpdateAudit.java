package com.example.toy.common.domain;

import java.time.temporal.Temporal;

public interface IUpdateAudit<T extends Temporal> {
  String getUpdaterCd();

  T getUpdatedAt();
}
