package com.example.toy.post.service;

import com.example.toy.common.exception.CustomException;
import com.example.toy.common.exception.ErrorCode;
import com.example.toy.post.dto.req.create.PostCreateRequestDto;
import com.example.toy.post.dto.req.update.PostUpdateRequestDto;
import com.example.toy.post.dto.res.PostResponseDto;
import com.example.toy.post.entity.Post;
import com.example.toy.post.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAllByOrderByUpdatedAtDesc()
                .stream()
                .map(PostResponseDto::fromEntity)
                .toList();
    }

    @Override
    public PostResponseDto createPost(PostCreateRequestDto postRequestDto) {
        Post post = postRepository.save(postRequestDto.toEntity());
        return PostResponseDto.fromEntity(post);
    }

    @Override
    public PostResponseDto getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return PostResponseDto.fromEntity(post);
    }

    @Override
    public long updatePost(PostUpdateRequestDto postUpdateRequestDto) {
        Post post = postRepository.findById(postUpdateRequestDto.getPostId())
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        return postRepository.updatePost(postUpdateRequestDto);
    }
}
