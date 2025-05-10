package com.example.toy.post.service;

import com.example.toy.common.exception.CustomException;
import com.example.toy.common.exception.ErrorCode;
import com.example.toy.common.response.CreatedData;
import com.example.toy.common.util.SecurityUtil;
import com.example.toy.post.dto.req.create.PostCreateRequestDto;
import com.example.toy.post.dto.req.delete.PostDeleteRequestDto;
import com.example.toy.post.dto.req.read.PostReadAllRequestDto;
import com.example.toy.post.dto.req.update.PostUpdateRequestDto;
import com.example.toy.post.dto.res.PostResponseDto;
import com.example.toy.post.entity.Post;
import com.example.toy.post.repository.PostRepository;
import com.example.toy.user.entity.User;
import com.example.toy.user.entity.UserRole;
import com.example.toy.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;
  private final UserRepository userRepository;

  /** 게시글 전체 개수 조회 */
  @Override
  public int getTotalCount(PostReadAllRequestDto postReadAllRequestDto) {
    postReadAllRequestDto.validate();
    List<Post> postList = postRepository.searchPost(postReadAllRequestDto);
    SecurityUtil.getCurrentUsername();
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
  public PostResponseDto getPostById(Integer postId) {
    Post post =
        postRepository
            .findById(Long.valueOf(postId))
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
  @Transactional
  public void updatePost(PostUpdateRequestDto postUpdateRequestDto) {
    postRepository
        .findById(postUpdateRequestDto.getPostId())
        .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
    postRepository.updatePost(postUpdateRequestDto);
  }

  /** 게시글 삭제 */
  @Override
  @Transactional
  public void deletePost(PostDeleteRequestDto postDeleteRequestDto) {

    Post post =
        postRepository
            .findById(postDeleteRequestDto.getPostId())
            .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

    String username = SecurityUtil.getCurrentUsername();

    User user =
        userRepository
            .findByUsername(username)
            .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

    if (UserRole.ADMIN.equals(user.getRole()) || post.getCreatorCd().equals(username)) {
      postRepository.deletePost(postDeleteRequestDto);
    } else {
      throw new CustomException(ErrorCode.ACCESS_DENIED);
    }
  }
}
