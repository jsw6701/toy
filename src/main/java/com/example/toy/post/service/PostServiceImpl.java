package com.example.toy.post.service;

import com.example.toy.post.dto.req.PostRequestDto;
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
    public PostResponseDto createPost(PostRequestDto postRequestDto) {
        Post post = postRepository.save(postRequestDto.toEntity());
        return PostResponseDto.fromEntity(post);
    }
}
