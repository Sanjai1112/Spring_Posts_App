package com.sanjai.posts.repositories;

import com.sanjai.posts.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comments,Long> {

    @Query(value = "select count(*) from comments",nativeQuery = true)
    Long getCounts();

    @Query(value = "SELECT * FROM COMMENTS WHERE POST_ID = ?1",nativeQuery = true)
    List<Comments> getAllCommentByPostId(Long id);

    @Query(value = "DELETE * FROM COMMENTS WHERE POST_ID = ?1", nativeQuery = true)
    void deleteAllCommentsByPostId(Long id);
}
