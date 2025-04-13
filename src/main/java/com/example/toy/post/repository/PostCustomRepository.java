package com.example.toy.post.repository;

import com.example.toy.post.dto.req.PostRequestDto;

public interface PostCustomRepository {
    long updatePost(Long postId, PostRequestDto postRequestDto);
}
