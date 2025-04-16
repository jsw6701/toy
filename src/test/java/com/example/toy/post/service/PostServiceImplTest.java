package com.example.toy.post.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.example.toy.common.exception.CustomException;
import com.example.toy.common.exception.ErrorCode;
import com.example.toy.post.dto.req.create.PostCreateRequestDto;
import com.example.toy.post.dto.req.delete.PostDeleteRequestDto;
import com.example.toy.post.dto.req.read.PostReadDetailRequestDto;
import com.example.toy.post.dto.req.update.PostUpdateRequestDto;
import com.example.toy.post.dto.res.PostResponseDto;
import com.example.toy.post.entity.Post;
import com.example.toy.post.repository.PostRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@SuppressWarnings("NonAsciiCharacters")
class PostServiceImplTest {

  @Mock private PostRepository postRepository;

  @InjectMocks private PostServiceImpl postService;

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
    Post post1 =
        Post.builder()
            .id(1L)
            .title("Title1")
            .content("Content1")
            .creatorCd("Creator1")
            .createdAt(LocalDateTime.now())
            .updaterCd("Updater1")
            .updatedAt(LocalDateTime.now())
            .build();

    Post post2 =
        Post.builder()
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
    PostCreateRequestDto requestDto =
        PostCreateRequestDto.builder().title("New Post").content("This is a new post.").build();

    Post post =
        Post.builder()
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
    PostUpdateRequestDto requestDto =
        PostUpdateRequestDto.builder()
            .postId(postId)
            .title("Updated Title")
            .content("Updated Content")
            .build();

    Post post =
        Post.builder()
            .id(postId)
            .title("Original Title")
            .content("Original Content")
            .creatorCd("Creator1")
            .createdAt(LocalDateTime.now())
            .updaterCd("Updater1")
            .updatedAt(LocalDateTime.now())
            .build();

    when(postRepository.findById(postId)).thenReturn(java.util.Optional.of(post));
    when(postRepository.updatePost(requestDto)).thenReturn(1L); // Mock updatePost 결과

    // When
    long result = postService.updatePost(requestDto);

    // Then
    assertEquals(1L, result); // 업데이트된 행의 개수 검증
    verify(postRepository, times(1)).findById(postId);
    verify(postRepository, times(1)).updatePost(requestDto);
  }

  @Test
  void 게시글_상세조회() {
    // Given
    Long postId = 1L;
    Post post =
        Post.builder()
            .id(postId)
            .title("Post Title")
            .content("Post Content")
            .creatorCd("Creator1")
            .createdAt(LocalDateTime.now())
            .updaterCd("Updater1")
            .updatedAt(LocalDateTime.now())
            .build();

    PostReadDetailRequestDto requestDto = PostReadDetailRequestDto.builder().postId(postId).build();

    when(postRepository.findById(postId)).thenReturn(java.util.Optional.of(post));

    // When
    PostResponseDto result = postService.getPostById(requestDto);

    // Then
    assertEquals("Post Title", result.getTitle());
    assertEquals("Post Content", result.getContent());
    verify(postRepository, times(1)).findById(postId);
  }

  @Test
  void 게시글_삭제() {
    // Given
    Long postId = 1L;
    Post post =
        Post.builder()
            .id(postId)
            .title("Post Title")
            .content("Post Content")
            .creatorCd("Creator1")
            .createdAt(LocalDateTime.now())
            .updaterCd("Updater1")
            .updatedAt(LocalDateTime.now())
            .build();

    PostDeleteRequestDto requestDto = PostDeleteRequestDto.builder().postId(postId).build();

    when(postRepository.findById(postId)).thenReturn(java.util.Optional.of(post));
    when(postRepository.deletePost(requestDto)).thenReturn(1L); // Mock deletePost 결과

    // When
    long result = postService.deletePost(requestDto);

    // Then
    assertEquals(1L, result); // 삭제된 행의 개수 검증
    verify(postRepository, times(1)).findById(postId);
    verify(postRepository, times(1)).deletePost(requestDto);
  }

  @Test
  void 게시글_삭제_실패() {
    // Given
    Long postId = 1L;
    Post post =
        Post.builder()
            .id(postId)
            .title("Post Title")
            .content("Post Content")
            .creatorCd("Creator1")
            .createdAt(LocalDateTime.now())
            .updaterCd("Updater1")
            .updatedAt(LocalDateTime.now())
            .build();

    PostDeleteRequestDto requestDto = PostDeleteRequestDto.builder().postId(postId).build();

    when(postRepository.findById(postId)).thenReturn(java.util.Optional.of(post));
    when(postRepository.deletePost(requestDto)).thenReturn(0L); // Mock deletePost 결과

    // When
    long result = postService.deletePost(requestDto);

    // Then
    assertEquals(0L, result); // 삭제된 행의 개수 검증
    verify(postRepository, times(1)).findById(postId);
    verify(postRepository, times(1)).deletePost(requestDto);
  }

  @Test
  void 게시글_상세조회_실패() {
    // Given
    Long postId = 1L;
    PostReadDetailRequestDto requestDto = PostReadDetailRequestDto.builder().postId(postId).build();

    when(postRepository.findById(postId)).thenReturn(java.util.Optional.empty());

    // When & Then
    CustomException exception =
        assertThrows(CustomException.class, () -> postService.getPostById(requestDto));
    assertEquals(ErrorCode.POST_NOT_FOUND.getCode(), exception.getCode());
  }

  @Test
  void 게시글_수정_실패() {
    // Given
    Long postId = 1L;
    PostUpdateRequestDto requestDto =
        PostUpdateRequestDto.builder()
            .postId(postId)
            .title("Updated Title")
            .content("Updated Content")
            .build();

    when(postRepository.findById(postId)).thenReturn(java.util.Optional.empty());

    // When & Then
    CustomException exception =
        assertThrows(CustomException.class, () -> postService.updatePost(requestDto));
    assertEquals(ErrorCode.POST_NOT_FOUND.getCode(), exception.getCode());
  }

  @Test
  void 게시글_생성_실패_제목없음() {
    // Given
    PostCreateRequestDto requestDto =
        PostCreateRequestDto.builder()
            .title(null) // 제목이 null인 경우
            .content("This is a new post.")
            .build();

    // When & Then
    assertThrows(NullPointerException.class, () -> postService.createPost(requestDto));
  }
}
