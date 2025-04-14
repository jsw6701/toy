package com.example.toy.post.controller;

import com.example.toy.post.dto.req.create.PostCreateRequestDto;
import com.example.toy.post.dto.req.delete.PostDeleteRequestDto;
import com.example.toy.post.dto.req.read.PostReadDetailRequestDto;
import com.example.toy.post.dto.req.update.PostUpdateRequestDto;
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

    @Override
    @PatchMapping("/delete")
    public ResponseEntity<Long> deletePost(@RequestBody PostDeleteRequestDto postDeleteRequestDto) {
        long length = postService.deletePost(postDeleteRequestDto);
        return ResponseEntity.ok(length);
    }

    @Override
    @PatchMapping("/update")
    public ResponseEntity<Long> updatePost(@RequestBody PostUpdateRequestDto postUpdateRequestDto) {
        long length = postService.updatePost(postUpdateRequestDto);
        return ResponseEntity.ok(length);
    }

    @Override
    @GetMapping("/detail")
    public ResponseEntity<PostResponseDto> getPostById(@RequestParam PostReadDetailRequestDto postReadDetailRequestDto) {
        PostResponseDto postResponseDto = postService.getPostById(postReadDetailRequestDto);
        return ResponseEntity.ok(postResponseDto);
    }

}
