package com.example.CodeFellowship.repositories;

import com.example.CodeFellowship.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepositry extends JpaRepository<Post,Long> {

}
