package com.example.CodeFellowship.Controller;

import com.example.CodeFellowship.models.ApplicationUser;
import com.example.CodeFellowship.models.Post;
import com.example.CodeFellowship.repositories.ApplicationUserRepo;
import com.example.CodeFellowship.repositories.PostRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
public class FollowController {
    @Autowired
    ApplicationUserRepo applicationUserRepo;
    @Autowired
    PostRepositry postRepositry;
    @PostMapping("/follow/{userId}")
    public RedirectView followUser(@PathVariable Long userId, Principal principal){
        ApplicationUser currentApplicationUser=applicationUserRepo.findByUsername(principal.getName());
        ApplicationUser wantFollowApplicationUser=applicationUserRepo.findById(userId).orElseThrow();
        if(currentApplicationUser!=null&&wantFollowApplicationUser!=null){
            wantFollowApplicationUser.getFollowers().add(currentApplicationUser);
            applicationUserRepo.save(wantFollowApplicationUser);
        }
        return new RedirectView("/users/"+userId);

    }

    @GetMapping("/feed")
    public String getAllFeed(Principal p , Model m){
        if (p != null)
        {
            ApplicationUser myUser = applicationUserRepo.findByUsername(p.getName());
            Set<ApplicationUser> followed = myUser.getFollowing();
            followed.remove(myUser);
            List<Post> posts = postRepositry.findAllByApplicationUserIn(followed);
            m.addAttribute("posts", posts);
        }
        return "feed";
    }




}
