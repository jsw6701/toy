package com.example.toy.post.service;

import com.example.toy.common.exception.CustomException;
import com.example.toy.common.exception.ErrorCode;
import com.example.toy.common.response.CreatedData;
import com.example.toy.post.dto.req.create.PostCreateRequestDto;
import com.example.toy.post.dto.req.delete.PostDeleteRequestDto;
import com.example.toy.post.dto.req.read.PostReadAllRequestDto;
import com.example.toy.post.dto.req.read.PostReadDetailRequestDto;
import com.example.toy.post.dto.req.update.PostUpdateRequestDto;
import com.example.toy.post.dto.res.PostResponseDto;
import com.example.toy.post.entity.Post;
import com.example.toy.post.repository.PostRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;

  /** 게시글 전체 개수 조회 */
  @Override
  public int getTotalCount(PostReadAllRequestDto postReadAllRequestDto) {
    postReadAllRequestDto.validate();
    List<Post> postList = postRepository.searchPost(postReadAllRequestDto);
    return postList.size();
  }

  /** 게시글 전체 조회 */
  @Override
  public List<PostResponseDto> getAllPosts(PostReadAllRequestDto postReadAllRequestDto) {
    postReadAllRequestDto.validate();
    return postRepository.searchPost(postReadAllRequestDto).stream()
        .map(PostResponseDto::fromEntity)
        .toList();
  }

  /** 게시글 상세 조회 */
  @Override
  public PostResponseDto getPostById(PostReadDetailRequestDto postReadDetailRequestDto) {
    postReadDetailRequestDto.validate();
    Post post =
        postRepository
            .findById(postReadDetailRequestDto.getPostId())
            .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
    return PostResponseDto.fromEntity(post);
  }

  /** 게시글 생성 */
  @Override
  public List<CreatedData> createPost(PostCreateRequestDto postRequestDto) {
    postRequestDto.validate();

    Post post = postRepository.save(postRequestDto.toEntity());

    List<CreatedData> createdDataList = new ArrayList<>();
    createdDataList.add(CreatedData.of(post.getId()));
    return createdDataList;
  }

  /** 게시글 수정 */
  @Override
  public void updatePost(PostUpdateRequestDto postUpdateRequestDto) {
    postRepository
        .findById(postUpdateRequestDto.getPostId())
        .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
    postRepository.updatePost(postUpdateRequestDto);
  }

  /** 게시글 삭제 */
  @Override
  public void deletePost(PostDeleteRequestDto postDeleteRequestDto) {
    postRepository
        .findById(postDeleteRequestDto.getPostId())
        .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

    postRepository.deletePost(postDeleteRequestDto);
  }
}
