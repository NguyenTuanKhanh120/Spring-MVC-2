package com.abc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abc.entities.Post;
import com.abc.entities.User;
import com.abc.services.FollowService;
import com.abc.services.PostService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/follow")
public class FollowController {

    private final FollowService followService;
    private final PostService postService;

    @Autowired
    public FollowController(FollowService followService, PostService postService) {
        this.followService = followService;
        this.postService = postService;
    }

    @PostMapping("/add")
    public String followUser(@RequestParam("followingUserId") int followingUserId,
                             @RequestParam("followedUserId") int followedUserId) {
        followService.followUser(followingUserId, followedUserId);
        return "redirect:/";
    }

    @PostMapping("/remove")
    public String unfollowUser(@RequestParam("followingUserId") int followingUserId,
                               @RequestParam("followedUserId") int followedUserId) {
        followService.unfollowUser(followingUserId, followedUserId);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String searchUsers(
            @RequestParam(value = "minFollowing", defaultValue = "3") int minFollowing,
            @RequestParam(value = "minFollowers", defaultValue = "5") int minFollowers,
            Model model,
            HttpSession session) {
        // Kiểm tra session
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        // Tìm kiếm người dùng
        List<User> searchResults = followService.searchUsersByFollowCriteria(minFollowing, minFollowers);
        model.addAttribute("searchResults", searchResults);

        // Set các thuộc tính cần thiết cho home.jsp
        List<User> suggestFollow = followService.getSuggestFollow(user.getId());
        List<User> usersf = followService.getUserFollower(user.getId());
        List<User> userfed = followService.getUserFollowed(user.getId());
        List<Post> posts = postService.getAllPost(user.getId());

        model.addAttribute("suggestfollow", suggestFollow);
        model.addAttribute("usersf", usersf);
        model.addAttribute("userfed", userfed);
        model.addAttribute("posts", posts);

        return "home";
    }
}