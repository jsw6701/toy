package com.example.toy.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record CreatedResponse<T>(
    @Schema(description = "created message") String message,
    @Schema(description = "created data") List<T> resultList) {}
