package com.example.toy.post.repository;

import com.example.toy.post.dto.req.delete.PostDeleteRequestDto;
import com.example.toy.post.dto.req.update.PostUpdateRequestDto;
import com.example.toy.post.entity.QPost;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository {
    private final EntityManager entityManager;

    /**
     * 게시글 수정
     */
    @Override
    public long updatePost(PostUpdateRequestDto postUpdateRequestDto) {
        QPost post = QPost.post;

        return new JPAUpdateClause(entityManager, post)
                .where(post.id.eq(postUpdateRequestDto.getPostId()))
                .set(post.title, postUpdateRequestDto.getTitle())
                .set(post.content, postUpdateRequestDto.getContent())
                .execute();
    }

    /**
     * 게시글 삭제
     */
    @Override
    public long deletePost(PostDeleteRequestDto postDeleteRequestDto) {
        QPost post = QPost.post;

        return new JPAUpdateClause(entityManager, post)
                .where(post.id.eq(postDeleteRequestDto.getPostId()))
                .set(post.isDeleted, "Y")
                .execute();
    }
}
