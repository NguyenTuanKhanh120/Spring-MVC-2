package com.abc.services;

import java.util.List;

import com.abc.entities.User;

public interface FollowService {
    List<User> getUserFollower(int id);
    List<User> getUserFollowed(int id);
    List<User> getSuggestFollow(int id);
    void followUser(int followingUserId, int followedUserId);
    void unfollowUser(int followingUserId, int followedUserId);
    // Thêm phương thức mới
    List<User> searchUsersByFollowCriteria(int minFollowing, int minFollowers);
}