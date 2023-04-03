package com.dipendra.blog.repository;

import com.dipendra.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository  extends JpaRepository<Post,Long> {
}
