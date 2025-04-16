package com.example.toy.post.service;

import com.example.toy.post.dto.req.create.PostCreateRequestDto;
import com.example.toy.post.dto.req.delete.PostDeleteRequestDto;
import com.example.toy.post.dto.req.read.PostReadDetailRequestDto;
import com.example.toy.post.dto.req.update.PostUpdateRequestDto;
import com.example.toy.post.dto.res.PostResponseDto;
import java.util.List;

public interface PostService {

  List<PostResponseDto> getAllPosts();

  PostResponseDto createPost(PostCreateRequestDto postRequestDto);

  PostResponseDto getPostById(PostReadDetailRequestDto postReadDetailRequestDto);

  long updatePost(PostUpdateRequestDto postUpdateRequestDto);

  long deletePost(PostDeleteRequestDto postDeleteRequestDto);
}
