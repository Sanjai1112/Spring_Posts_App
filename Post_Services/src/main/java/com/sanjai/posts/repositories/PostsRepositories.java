package com.sanjai.posts.repositories;

import com.sanjai.posts.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepositories extends JpaRepository<Posts,Long> {
}
