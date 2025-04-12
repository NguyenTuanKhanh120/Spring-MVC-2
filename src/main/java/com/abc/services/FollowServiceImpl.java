package com.abc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.dao.FollowDAO;
import com.abc.entities.User;

@Service
public class FollowServiceImpl implements FollowService {

    private final FollowDAO followDAO;

    @Autowired
    public FollowServiceImpl(FollowDAO followDAO) {
        this.followDAO = followDAO;
    }

    @Override
    public List<User> getUserFollower(int id) {
        return followDAO.getFollowerUser(id);
    }

    @Override
    public List<User> getUserFollowed(int id) {
        return followDAO.getFollowedUsers(id);
    }

    @Override
    public List<User> getSuggestFollow(int id) {
        return followDAO.getSuggestedFollows(id);
    }

    @Override
    public void followUser(int followingUserId, int followedUserId) {
        followDAO.followUser(followingUserId, followedUserId);
    }

    @Override
    public void unfollowUser(int followingUserId, int followedUserId) {
        followDAO.unfollowUser(followingUserId, followedUserId);
    }

    @Override
    public List<User> searchUsersByFollowCriteria(int minFollowing, int minFollowers) {
        return followDAO.searchUsersByFollowCriteria(minFollowing, minFollowers);
    }
}