package com.example.toy.post.controller;

import com.example.toy.post.dto.req.create.PostCreateRequestDto;
import com.example.toy.post.dto.res.PostResponseDto;
import com.example.toy.post.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController implements PostSwagger{

    private final PostService postService;

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        List<PostResponseDto> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostCreateRequestDto post) {
        PostResponseDto postResponseDto = postService.createPost(post);
        return ResponseEntity.ok(postResponseDto);
    }

}
