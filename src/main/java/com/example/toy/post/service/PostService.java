package com.example.toy.post.service;

import com.example.toy.post.dto.req.PostRequestDto;
import com.example.toy.post.dto.res.PostResponseDto;

import java.util.List;

public interface PostService {

    List<PostResponseDto> getAllPosts();

    PostResponseDto createPost(PostRequestDto postRequestDto);
}
