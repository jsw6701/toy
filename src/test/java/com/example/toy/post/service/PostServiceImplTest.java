package com.example.toy.post.service;

import com.example.toy.post.dto.req.PostRequestDto;
import com.example.toy.post.dto.res.PostResponseDto;
import com.example.toy.post.entity.Post;
import com.example.toy.post.repository.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SuppressWarnings("NonAsciiCharacters")
class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImpl postService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void 게시글_전체조회() {
        // Given
        Post post1 = Post.builder()
                .id(1L)
                .title("Title1")
                .content("Content1")
                .creatorCd("Creator1")
                .createdAt(LocalDateTime.now())
                .updaterCd("Updater1")
                .updatedAt(LocalDateTime.now())
                .build();

        Post post2 = Post.builder()
                .id(2L)
                .title("Title2")
                .content("Content2")
                .creatorCd("Creator2")
                .createdAt(LocalDateTime.now())
                .updaterCd("Updater2")
                .updatedAt(LocalDateTime.now())
                .build();

        when(postRepository.findAllByOrderByUpdatedAtDesc()).thenReturn(Arrays.asList(post1, post2));

        // When
        List<PostResponseDto> result = postService.getAllPosts();

        // Then
        assertEquals(2, result.size());
        assertEquals("Title1", result.get(0).getTitle());
        assertEquals("Title2", result.get(1).getTitle());
        verify(postRepository, times(1)).findAllByOrderByUpdatedAtDesc();
    }

    @Test
    void 게시글_생성() {
        // Given
        PostRequestDto requestDto = PostRequestDto.builder()
                .title("New Post")
                .content("This is a new post.")
                .build();

        Post post = Post.builder()
                .id(1L)
                .title("New Post")
                .content("This is a new post.")
                .creatorCd("Creator1")
                .createdAt(LocalDateTime.now())
                .updaterCd("Updater1")
                .updatedAt(LocalDateTime.now())
                .build();

        when(postRepository.save(any(Post.class))).thenReturn(post);

        // When
        PostResponseDto result = postService.createPost(requestDto);

        // Then
        assertEquals("New Post", result.getTitle());
        assertEquals("This is a new post.", result.getContent());
        verify(postRepository, times(1)).save(any(Post.class));
    }

    @Test
    void 게시글_수정() {
        // Given
        Long postId = 1L;
        PostRequestDto requestDto = PostRequestDto.builder()
                .title("Updated Title")
                .content("Updated Content")
                .build();

        Post post = Post.builder()
                .id(postId)
                .title("Original Title")
                .content("Original Content")
                .creatorCd("Creator1")
                .createdAt(LocalDateTime.now())
                .updaterCd("Updater1")
                .updatedAt(LocalDateTime.now())
                .build();

        when(postRepository.findById(postId)).thenReturn(java.util.Optional.of(post));
        when(postRepository.updatePost(postId, requestDto)).thenReturn(1L); // Mock updatePost 결과

        // When
        long result = postService.updatePost(postId, requestDto);

        // Then
        assertEquals(1L, result); // 업데이트된 행의 개수 검증
        verify(postRepository, times(1)).findById(postId);
        verify(postRepository, times(1)).updatePost(postId, requestDto);
    }
}