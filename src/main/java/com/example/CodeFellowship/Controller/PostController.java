package com.example.CodeFellowship.Controller;

import com.example.CodeFellowship.models.ApplicationUser;
import com.example.CodeFellowship.models.Post;
import com.example.CodeFellowship.repositories.ApplicationUserRepo;
import com.example.CodeFellowship.repositories.PostRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class PostController {
    @Autowired
    ApplicationUserRepo applicationUserRepo;
    @Autowired
    PostRepositry  postRepositry;


    @Autowired
    private HttpServletRequest request;
    @GetMapping("/myprofile")
    public String getMyProfile(Principal p, Model m) {
        if (p != null) {
            String username = p.getName();
            ApplicationUser user = applicationUserRepo.findByUsername(username);
            if (user != null) {
                m.addAttribute("user", user);
                m.addAttribute("post", user.getPostList());
            }
        }

        return "myprofile.html";
    }
    @GetMapping("/users/{id}")
    public String getUsers(Principal p, Model m,@PathVariable Long id) {
        if (p != null) {
            String username = p.getName();
            ApplicationUser user = applicationUserRepo.findByUsername(username);
            m.addAttribute("user", user);
        }
        ApplicationUser applicationUser=applicationUserRepo.findById(id).orElseThrow();
                m.addAttribute("applicationUser", applicationUser);
                m.addAttribute("post", applicationUser.getPostList());



        return "users.html";
    }
    @GetMapping("/users")
    public String getAllUsers(Principal p, Model m) {
        if (p != null) {
//            String username = p.getName();
//            ApplicationUser user = applicationUserRepo.findByUsername(username);
//            m.addAttribute("user", user);
            List<ApplicationUser> applicationUser=applicationUserRepo.findAll();
            m.addAttribute("applicationUser", applicationUser);
        }

//        m.addAttribute("post", applicationUser.getPostList());



        return "allUsers.html";
    }

    @PostMapping("/addPost")
    public RedirectView addPost(Principal p, String body) {
        if (p != null) {
            String username = p.getName();
            ApplicationUser applicationUser = applicationUserRepo.findByUsername(username);

            if (applicationUser != null) {
                Post post = new Post(body, applicationUser);
                post.setCreatedAt(LocalDate.now());
                postRepositry.save(post);
                return new RedirectView("/myprofile");
            }
        }
        return new RedirectView("/myprofile");
    }

}
