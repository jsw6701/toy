package com.example.toy.common.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record DetailResponse<T>(@Schema(description = "응답 데이터") T result) {}
