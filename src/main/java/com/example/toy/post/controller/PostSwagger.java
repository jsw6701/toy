package com.example.toy.post.controller;

import com.example.toy.common.response.CreatedResponse;
import com.example.toy.common.response.ResponseData;
import com.example.toy.post.dto.req.create.PostCreateRequestDto;
import com.example.toy.post.dto.req.delete.PostDeleteRequestDto;
import com.example.toy.post.dto.req.read.PostReadDetailRequestDto;
import com.example.toy.post.dto.req.update.PostUpdateRequestDto;
import com.example.toy.post.dto.res.PostResponseDto;
import com.example.toy.post.entity.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface PostSwagger {
  @Operation(summary = "모든 게시글 조회", description = "모든 게시글을 조회합니다.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "성공적으로 조회됨",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Post.class)))
      })
  ResponseEntity<List<PostResponseDto>> getAllPosts();

  @Operation(summary = "게시글 작성", description = "게시글을 작성합니다.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "게시글 작성 성공",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Post.class))),
        @ApiResponse(
            responseCode = "400",
            description = "잘못된 요청",
            content = @Content(mediaType = "application/json"))
      })
  ResponseData<CreatedResponse<String>> createPost(@RequestBody @Valid PostCreateRequestDto post);

  @Operation(summary = "게시글 상세 조회", description = "게시글을 상세 조회합니다.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "게시글 상세 조회 성공",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Post.class))),
        @ApiResponse(
            responseCode = "404",
            description = "게시글을 찾을 수 없음",
            content = @Content(mediaType = "application/json"))
      })
  ResponseEntity<PostResponseDto> getPostById(
      @RequestBody @Valid PostReadDetailRequestDto postReadDetailRequestDto);

  @Operation(summary = "게시글 수정", description = "게시글을 수정합니다.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "게시글 수정 성공",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Post.class))),
        @ApiResponse(
            responseCode = "400",
            description = "잘못된 요청",
            content = @Content(mediaType = "application/json"))
      })
  ResponseEntity<Long> updatePost(@RequestBody @Valid PostUpdateRequestDto postUpdateRequestDto);

  @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "게시글 삭제 성공",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Post.class))),
        @ApiResponse(
            responseCode = "404",
            description = "게시글을 찾을 수 없음",
            content = @Content(mediaType = "application/json"))
      })
  ResponseEntity<Long> deletePost(@RequestBody @Valid PostDeleteRequestDto postDeleteRequestDto);
}
