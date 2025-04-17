package com.example.toy.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record PagingResponse<T>(
    @Schema(description = "응답 데이터") List<T> resultList,
    @Schema(description = "전체 데이터 수", example = "10") Integer totalCount,
    @Schema(description = "현재 페이지 번호", example = "2") Integer currentPageNo,
    @Schema(description = "시작 페이지 번호", example = "1") Integer firstPageNo,
    @Schema(description = "마지막 페이지 번호", example = "10") Integer lastPageNo) {}
