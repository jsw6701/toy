package com.example.toy.post.repository;

import com.example.toy.post.dto.req.delete.PostDeleteRequestDto;
import com.example.toy.post.dto.req.read.PostReadAllRequestDto;
import com.example.toy.post.dto.req.update.PostUpdateRequestDto;
import com.example.toy.post.entity.Post;
import java.util.List;

public interface PostCustomRepository {
  List<Post> searchPost(PostReadAllRequestDto postReadAllRequestDto);

  long updatePost(PostUpdateRequestDto postUpdateRequestDto);

  long deletePost(PostDeleteRequestDto postDeleteRequestDto);
}
