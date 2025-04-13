package com.example.toy.post.repository;

import com.example.toy.post.dto.req.PostRequestDto;
import com.example.toy.post.entity.QPost;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository {
    private final EntityManager entityManager;

    @Override
    public long updatePost(Long postId, PostRequestDto postRequestDto) {
        QPost post = QPost.post;

        return new JPAUpdateClause(entityManager, post)
                .where(post.id.eq(postId))
                .set(post.title, postRequestDto.getTitle())
                .set(post.content, postRequestDto.getContent())
                .execute();
    }

}
