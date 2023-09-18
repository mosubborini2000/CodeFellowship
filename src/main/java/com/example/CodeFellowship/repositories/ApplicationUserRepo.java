package com.example.CodeFellowship.repositories;

import com.example.CodeFellowship.models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepo extends JpaRepository<ApplicationUser,Long> {

    ApplicationUser findByUsername(String username);
}
