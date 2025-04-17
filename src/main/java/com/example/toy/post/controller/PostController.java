package com.example.toy.post.controller;

import com.example.toy.common.response.CreatedResponse;
import com.example.toy.common.response.PagingResponse;
import com.example.toy.common.response.ResponseData;
import com.example.toy.common.response.ResponseUtils;
import com.example.toy.common.validator.GlobalMessages;
import com.example.toy.post.PostRequestMapper;
import com.example.toy.post.dto.req.create.PostCreateRequestDto;
import com.example.toy.post.dto.req.delete.PostDeleteRequestDto;
import com.example.toy.post.dto.req.read.PostReadAllRequestDto;
import com.example.toy.post.dto.req.read.PostReadDetailRequestDto;
import com.example.toy.post.dto.req.update.PostUpdateRequestDto;
import com.example.toy.post.dto.res.PostResponseDto;
import com.example.toy.post.service.PostService;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/post")
@Validated
public class PostController implements PostSwagger {

  private final GlobalMessages globalMessages;
  private final PostService postService;
  private static final String LABEL_POST_REGISTER = "label.post.register";

  @Override
  @GetMapping("/all")
  public ResponseData<PagingResponse<PostResponseDto>> search(
      @RequestParam(value = "pageNo", required = false) Integer pageNo,
      @RequestParam(value = "pageRow", required = false) Integer pageRow,
      @RequestParam(value = "isDeleted", required = false) String isDeleted) {

    PostReadAllRequestDto dtoTotalCount =
        PostRequestMapper.INSTANCE.toPostReadAllRequestDto(isDeleted);
    int totalCount = postService.getTotalCount(dtoTotalCount);

    PostReadAllRequestDto command =
        PostRequestMapper.INSTANCE.toPostReadAllRequestDto(
            dtoTotalCount, isDeleted, pageNo, pageRow, totalCount);
    List<PostResponseDto> posts = postService.getAllPosts(command);

    return ResponseUtils.data(
        new PagingResponse<>(
            posts,
            totalCount,
            command.getCurrentPageNo(),
            command.getFirstPageNo(),
            command.getLastPageNo()));
  }

  @Override
  @PostMapping("/create")
  public ResponseData<CreatedResponse<String>> createPost(
      @RequestBody @Valid PostCreateRequestDto post) {
    PostResponseDto postResponseDto = postService.createPost(post);

    String message =
        globalMessages.getMessage(
            "msg.save.success", new String[] {globalMessages.getMessage(LABEL_POST_REGISTER)});

    List<String> test = new ArrayList<>();
    return ResponseUtils.data(new CreatedResponse<>(message, test));
  }

  @Override
  @PatchMapping("/delete")
  public ResponseEntity<Long> deletePost(
      @RequestBody @Valid PostDeleteRequestDto postDeleteRequestDto) {
    long length = postService.deletePost(postDeleteRequestDto);
    return ResponseEntity.ok(length);
  }

  @Override
  @PatchMapping("/update")
  public ResponseEntity<Long> updatePost(
      @RequestBody @Valid PostUpdateRequestDto postUpdateRequestDto) {
    long length = postService.updatePost(postUpdateRequestDto);
    return ResponseEntity.ok(length);
  }

  @Override
  @GetMapping("/detail")
  public ResponseEntity<PostResponseDto> getPostById(
      @RequestBody @Valid PostReadDetailRequestDto postReadDetailRequestDto) {
    PostResponseDto postResponseDto = postService.getPostById(postReadDetailRequestDto);
    return ResponseEntity.ok(postResponseDto);
  }
}
