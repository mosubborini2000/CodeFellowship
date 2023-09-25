package com.example.CodeFellowship.repositories;

import com.example.CodeFellowship.models.ApplicationUser;
import com.example.CodeFellowship.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PostRepositry extends JpaRepository<Post,Long> {

    List<Post> findAllByApplicationUserIn(Set<ApplicationUser> followingUsers);
}
