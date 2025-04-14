package com.example.toy.post.service;

import com.example.toy.post.dto.req.create.PostCreateRequestDto;
import com.example.toy.post.dto.req.update.PostUpdateRequestDto;
import com.example.toy.post.dto.res.PostResponseDto;

import java.util.List;

public interface PostService {

    List<PostResponseDto> getAllPosts();

    PostResponseDto createPost(PostCreateRequestDto postRequestDto);

    PostResponseDto getPostById(Long postId);

    long updatePost(PostUpdateRequestDto postUpdateRequestDto);
}
