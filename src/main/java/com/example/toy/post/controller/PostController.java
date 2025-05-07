package com.example.toy.post.controller;

import com.example.toy.common.response.*;
import com.example.toy.common.validator.GlobalMessages;
import com.example.toy.post.PostRequestMapper;
import com.example.toy.post.dto.req.create.PostCreateRequestDto;
import com.example.toy.post.dto.req.delete.PostDeleteRequestDto;
import com.example.toy.post.dto.req.read.PostReadAllRequestDto;
import com.example.toy.post.dto.req.update.PostUpdateRequestDto;
import com.example.toy.post.dto.res.PostResponseDto;
import com.example.toy.post.service.PostService;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
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
  @GetMapping("/detail")
  public ResponseData<DetailResponse<PostResponseDto>> getPostById(
      @RequestParam(value = "postId", required = false) Integer postId) {

    PostResponseDto postResponseDto = postService.getPostById(postId);

    return ResponseUtils.data(new DetailResponse<>(postResponseDto));
  }

  @Override
  @PostMapping("/create")
  public ResponseData<CreatedResponse<CreatedData>> createPost(
      @RequestBody @Valid PostCreateRequestDto post) {

    List<CreatedData> createdDataList = postService.createPost(post);

    String message =
        globalMessages.getMessage(
            "msg.save.success.create",
            new String[] {globalMessages.getMessage(LABEL_POST_REGISTER)});
    return ResponseUtils.data(new CreatedResponse<>(message, createdDataList));
  }

  @Override
  @PatchMapping("/update")
  public ResponseData<CreatedResponse<CreatedData>> updatePost(
      @RequestBody @Valid PostUpdateRequestDto postUpdateRequestDto) {

    postService.updatePost(postUpdateRequestDto);

    String message =
        globalMessages.getMessage(
            "msg.save.success.update",
            new String[] {globalMessages.getMessage(LABEL_POST_REGISTER)});
    return ResponseUtils.data(new CreatedResponse<>(message, new ArrayList<>()));
  }

  @Override
  @PatchMapping("/delete")
  public ResponseData<CreatedResponse<CreatedData>> deletePost(
      @RequestBody @Valid PostDeleteRequestDto postDeleteRequestDto) {

    postService.deletePost(postDeleteRequestDto);

    String message =
        globalMessages.getMessage(
            "msg.save.success.delete",
            new String[] {globalMessages.getMessage(LABEL_POST_REGISTER)});
    return ResponseUtils.data(new CreatedResponse<>(message, new ArrayList<>()));
  }
}
