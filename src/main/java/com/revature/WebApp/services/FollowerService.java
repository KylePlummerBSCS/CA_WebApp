package com.revature.WebApp.services;

import com.revature.WebApp.DTO.FollowerDetailDTO;
import com.revature.WebApp.entities.FollowsListEntity;
import com.revature.WebApp.entities.UserEntity;
import com.revature.WebApp.repositories.FollowsDetailRepository;
import com.revature.WebApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FollowerService {

    private FollowsDetailRepository followRepo;
    private UserRepository userRepo;

    @Autowired
    public FollowerService(FollowsDetailRepository followRepo, UserRepository userRepo) {
        this.followRepo = followRepo;
        this.userRepo = userRepo;
    }

    public List<FollowsListEntity> findAllFollowers(){
        return followRepo.findAll();
    }


    public List<FollowerDetailDTO> getAllFollowerDetail(Integer userId){
        List<UserEntity> searchResults = userRepo.getFollowerDetail(userId);
        System.out.println("Debug: " + searchResults.size());
        List<FollowerDetailDTO> followers = new ArrayList<>();
        for (UserEntity user: searchResults) {
            System.out.println(user.getUsername());
            followers.add(new FollowerDetailDTO(user));
        }

        return followers;
    }

}
