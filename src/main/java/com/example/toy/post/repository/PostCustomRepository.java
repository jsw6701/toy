package com.example.toy.post.repository;

import com.example.toy.post.dto.req.update.PostUpdateRequestDto;

public interface PostCustomRepository {
    long updatePost(PostUpdateRequestDto postUpdateRequestDto);
}
