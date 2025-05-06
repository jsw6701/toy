package com.example.toy.post.controller;

import com.example.toy.common.response.*;
import com.example.toy.common.response.model.SuccessResponse;
import com.example.toy.post.dto.req.create.PostCreateRequestDto;
import com.example.toy.post.dto.req.delete.PostDeleteRequestDto;
import com.example.toy.post.dto.req.update.PostUpdateRequestDto;
import com.example.toy.post.dto.res.PostResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;

public interface PostSwagger {

  class PostPagingSearch extends SuccessResponse<ResponseData<PagingResponse<PostResponseDto>>> {}

  class PostCreatedData extends SuccessResponse<ResponseData<CreatedResponse<String>>> {}

  class PostDetailResponse extends SuccessResponse<ResponseData<DetailResponse<PostResponseDto>>> {}

  @Operation(summary = "모든 게시글 조회", description = "모든 게시글을 조회합니다.")
  @Parameter(
      name = "pageNo",
      description = "페이지 번호",
      in = ParameterIn.QUERY,
      schema = @Schema(type = "integer", example = "1"))
  @Parameter(
      name = "pageRow",
      description = "페이지당 게시글 수",
      in = ParameterIn.QUERY,
      schema = @Schema(type = "integer", example = "10"))
  @Parameter(
      name = "isDeleted",
      description = "삭제된 게시글 포함 여부",
      in = ParameterIn.QUERY,
      schema = @Schema(type = "string", example = "N"))
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "성공적으로 조회됨",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = PostSwagger.PostPagingSearch.class))),
        @ApiResponse(
            responseCode = "400",
            description = "BAD_REQUEST",
            content =
                @Content(
                    examples = {
                      @ExampleObject(
                          value =
                              """
                                            {
                                                "code": "BAD_REQUEST",
                                                "message": "BAD_REQUEST",
                                                "status": 400,
                                                "timestamp": 1717128250126,
                                                "errors": [
                                                    {
                                                        "field": "title",
                                                        "value": "",
                                                        "message": "필수 파라메터 값이 없습니다.[제목, {1}]"
                                                    }
                                                ]
                                            }
                                            """)
                    },
                    mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(
            responseCode = "500",
            description = "SERVER_ERROR",
            content =
                @Content(
                    examples = {
                      @ExampleObject(
                          value =
                              """
                                                {
                                                    "code": "SERVER_ERROR",
                                                    "message": "SERVER_ERROR",
                                                    "status": 500,
                                                    "timestamp": 1717128250126,
                                                    "errors": [
                                                    {
                                                        "field": "",
                                                        "value": "",
                                                        "message": "서버에서 오류가 발생했습니다."
                                                        }
                                                    ]
                                                }
                                                """)
                    },
                    mediaType = MediaType.APPLICATION_JSON_VALUE))
      })
  ResponseData<PagingResponse<PostResponseDto>> search(
      @RequestParam(value = "pageNo", required = false) Integer pageNo,
      @RequestParam(value = "pageRow", required = false) Integer pageRow,
      @RequestParam(value = "isDeleted", required = false) String isDeleted);

  @Operation(summary = "게시글 상세 조회", description = "게시글을 상세 조회합니다.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "게시글 상세 조회 성공",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = PostDetailResponse.class))),
        @ApiResponse(
            responseCode = "400",
            description = "BAD_REQUEST",
            content =
                @Content(
                    examples = {
                      @ExampleObject(
                          value =
                              """
                                                                          {
                                                                              "code": "BAD_REQUEST",
                                                                              "message": "BAD_REQUEST",
                                                                              "status": 400,
                                                                              "timestamp": 1717128250126,
                                                                              "errors": [
                                                                                  {
                                                                                      "field": "title",
                                                                                      "value": "",
                                                                                      "message": "필수 파라메터 값이 없습니다.[제목, {1}]"
                                                                                  }
                                                                              ]
                                                                          }
                                                                          """)
                    },
                    mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(
            responseCode = "500",
            description = "SERVER_ERROR",
            content =
                @Content(
                    examples = {
                      @ExampleObject(
                          value =
                              """
                                                                              {
                                                                                  "code": "SERVER_ERROR",
                                                                                  "message": "SERVER_ERROR",
                                                                                  "status": 500,
                                                                                  "timestamp": 1717128250126,
                                                                                  "errors": [
                                                                                  {
                                                                                      "field": "",
                                                                                      "value": "",
                                                                                      "message": "서버에서 오류가 발생했습니다."
                                                                                      }
                                                                                  ]
                                                                              }
                                                                              """)
                    },
                    mediaType = MediaType.APPLICATION_JSON_VALUE))
      })
  ResponseData<DetailResponse<PostResponseDto>> getPostById(
      @RequestParam Integer postId);

  @Operation(summary = "게시글 작성", description = "게시글을 작성합니다.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "SUCCESS",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = PostCreatedData.class))),
        @ApiResponse(
            responseCode = "400",
            description = "BAD_REQUEST",
            content =
                @Content(
                    examples = {
                      @ExampleObject(
                          value =
                              """
                                                {
                                                    "code": "BAD_REQUEST",
                                                    "message": "BAD_REQUEST",
                                                    "status": 400,
                                                    "timestamp": 1717128250126,
                                                    "errors": [
                                                        {
                                                            "field": "title",
                                                            "value": "",
                                                            "message": "필수 파라메터 값이 없습니다.[제목, {1}]"
                                                        }
                                                    ]
                                                }
                                                """)
                    },
                    mediaType = "application/json")),
        @ApiResponse(
            responseCode = "500",
            description = "SERVER_ERROR",
            content =
                @Content(
                    examples = {
                      @ExampleObject(
                          value =
                              """
                                                {
                                                    "code": "SERVER_ERROR",
                                                    "message": "SERVER_ERROR",
                                                    "status": 500,
                                                    "timestamp": 1717128250126,
                                                    "errors": [
                                                        {
                                                            "field": "",
                                                            "value": "",
                                                            "message": "서버에서 오류가 발생했습니다."
                                                        }
                                                    ]
                                                }
                                                """)
                    },
                    mediaType = MediaType.APPLICATION_JSON_VALUE))
      })
  ResponseData<CreatedResponse<CreatedData>> createPost(
      @RequestBody @Valid PostCreateRequestDto post);

  @Operation(summary = "게시글 수정", description = "게시글을 수정합니다.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "SUCCESS",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = PostCreatedData.class))),
        @ApiResponse(
            responseCode = "400",
            description = "BAD_REQUEST",
            content =
                @Content(
                    examples = {
                      @ExampleObject(
                          value =
                              """
                                                                            {
                                                                                "code": "BAD_REQUEST",
                                                                                "message": "BAD_REQUEST",
                                                                                "status": 400,
                                                                                "timestamp": 1717128250126,
                                                                                "errors": [
                                                                                    {
                                                                                        "field": "title",
                                                                                        "value": "",
                                                                                        "message": "필수 파라메터 값이 없습니다.[제목, {1}]"
                                                                                    }
                                                                                ]
                                                                            }
                                                                            """)
                    },
                    mediaType = "application/json")),
        @ApiResponse(
            responseCode = "500",
            description = "SERVER_ERROR",
            content =
                @Content(
                    examples = {
                      @ExampleObject(
                          value =
                              """
                                                                            {
                                                                                "code": "SERVER_ERROR",
                                                                                "message": "SERVER_ERROR",
                                                                                "status": 500,
                                                                                "timestamp": 1717128250126,
                                                                                "errors": [
                                                                                    {
                                                                                        "field": "",
                                                                                        "value": "",
                                                                                        "message": "서버에서 오류가 발생했습니다."
                                                                                    }
                                                                                ]
                                                                            }
                                                                            """)
                    },
                    mediaType = MediaType.APPLICATION_JSON_VALUE))
      })
  ResponseData<CreatedResponse<CreatedData>> updatePost(
      @RequestBody @Valid PostUpdateRequestDto postUpdateRequestDto);

  @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "SUCCESS",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = PostCreatedData.class))),
        @ApiResponse(
            responseCode = "400",
            description = "BAD_REQUEST",
            content =
                @Content(
                    examples = {
                      @ExampleObject(
                          value =
                              """
                                                                            {
                                                                                "code": "BAD_REQUEST",
                                                                                "message": "BAD_REQUEST",
                                                                                "status": 400,
                                                                                "timestamp": 1717128250126,
                                                                                "errors": [
                                                                                    {
                                                                                        "field": "title",
                                                                                        "value": "",
                                                                                        "message": "필수 파라메터 값이 없습니다.[제목, {1}]"
                                                                                    }
                                                                                ]
                                                                            }
                                                                            """)
                    },
                    mediaType = "application/json")),
        @ApiResponse(
            responseCode = "500",
            description = "SERVER_ERROR",
            content =
                @Content(
                    examples = {
                      @ExampleObject(
                          value =
                              """
                                                                            {
                                                                                "code": "SERVER_ERROR",
                                                                                "message": "SERVER_ERROR",
                                                                                "status": 500,
                                                                                "timestamp": 1717128250126,
                                                                                "errors": [
                                                                                    {
                                                                                        "field": "",
                                                                                        "value": "",
                                                                                        "message": "서버에서 오류가 발생했습니다."
                                                                                    }
                                                                                ]
                                                                            }
                                                                            """)
                    },
                    mediaType = MediaType.APPLICATION_JSON_VALUE))
      })
  ResponseData<CreatedResponse<CreatedData>> deletePost(
      @RequestBody @Valid PostDeleteRequestDto postDeleteRequestDto);
}
