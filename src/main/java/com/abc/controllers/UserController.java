package com.abc.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.abc.entities.Post;
import com.abc.entities.User;
import com.abc.services.PostService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
    
    private PostService postService;
    
    @Autowired
    public UserController(PostService postService) {
		this.postService = postService;
	}
	@GetMapping("/profile")
	public String profileUser(Model model,HttpSession session) {
		User user = (User) session.getAttribute("user");
		
		if(user == null)
			return "redirect:/login";
		
		List<Post> posts = new ArrayList<Post>();
		
		posts = postService.getPostById(user.getId());
		
		
		model.addAttribute("user",user);
		model.addAttribute("posts",posts);
		
		return "profile";
		
	}
}