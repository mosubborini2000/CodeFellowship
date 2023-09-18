package com.example.CodeFellowship.config;

import com.example.CodeFellowship.models.ApplicationUser;
import com.example.CodeFellowship.repositories.ApplicationUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    ApplicationUserRepo applicationUserRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser user=applicationUserRepo.findByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException("user not found: "+username);
        }


        return user;

    }
}
