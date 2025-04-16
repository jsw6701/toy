package com.example.toy.post.repository;

import com.example.toy.post.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {

  List<Post> findAllByOrderByUpdatedAtDesc();
}
