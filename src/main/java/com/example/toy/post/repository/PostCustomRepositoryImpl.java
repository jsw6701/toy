package com.example.toy.post.repository;

import com.example.toy.post.dto.req.delete.PostDeleteRequestDto;
import com.example.toy.post.dto.req.read.PostReadAllRequestDto;
import com.example.toy.post.dto.req.update.PostUpdateRequestDto;
import com.example.toy.post.entity.Post;
import com.example.toy.post.entity.QPost;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository {
  private final EntityManager entityManager;

  @Override
  public List<Post> searchPost(PostReadAllRequestDto postReadAllRequestDto) {
    QPost post = QPost.post;
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

    BooleanBuilder builder = new BooleanBuilder();

    // DTO가 null인 경우 기본값 설정
    if (postReadAllRequestDto == null) {
      postReadAllRequestDto = new PostReadAllRequestDto();
    }

    // 게시글 삭제 여부
    if (StringUtils.hasText(postReadAllRequestDto.getIsDeleted())) {
      builder.and(post.isDeleted.eq(postReadAllRequestDto.getIsDeleted()));
    }

    // 페이징 조건
    if (postReadAllRequestDto.getPageRow() > 0) {
      builder.and(post.id.gt(postReadAllRequestDto.getPageNo()));
    }
    if (postReadAllRequestDto.getOffsetNo() > -1) {
      builder.and(post.id.lt(postReadAllRequestDto.getOffsetNo()));
    }

    var query = queryFactory.select(post).from(post).where(builder).orderBy(post.updatedAt.desc());

    // limit, offset 조건 적용
    if (postReadAllRequestDto.getPageRow() > 0 && postReadAllRequestDto.getOffsetNo() > -1) {
      query =
          query
              .offset(Math.max(postReadAllRequestDto.getPageRow(), 0))
              .limit(
                  postReadAllRequestDto.getOffsetNo() > -1
                      ? postReadAllRequestDto.getOffsetNo()
                      : Long.MAX_VALUE);
    }

    return query.fetch();
  }

  /** 게시글 수정 */
  @Override
  public long updatePost(PostUpdateRequestDto postUpdateRequestDto) {
    QPost post = QPost.post;

    return new JPAUpdateClause(entityManager, post)
        .where(post.id.eq(postUpdateRequestDto.getPostId()))
        .set(post.title, postUpdateRequestDto.getTitle())
        .set(post.content, postUpdateRequestDto.getContent())
        .execute();
  }

  /** 게시글 삭제 */
  @Override
  public long deletePost(PostDeleteRequestDto postDeleteRequestDto) {
    QPost post = QPost.post;

    return new JPAUpdateClause(entityManager, post)
        .where(post.id.eq(postDeleteRequestDto.getPostId()))
        .set(post.isDeleted, "Y")
        .execute();
  }
}
