package com.revature.WebApp.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.WebApp.DTO.FollowerDetailDTO;
import com.revature.WebApp.DTO.MovieReviewDTO;
import com.revature.WebApp.DTO.Principal;
import com.revature.WebApp.entities.FollowsListEntity;
import com.revature.WebApp.entities.WatchHistoryEntity;
import com.revature.WebApp.security.TokenParser;
import com.revature.WebApp.services.FollowerService;
import com.revature.WebApp.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class ProfileDetailsController {

    private final FollowerService followerService;
    private final ProfileService profileService;
    private final TokenParser tokenParser;
    private final ObjectMapper objectMapper;

    @Autowired
    public ProfileDetailsController(FollowerService followerService, ProfileService profileService, TokenParser tokenParser){
        this.followerService = followerService;
        this.profileService = profileService;
        this.tokenParser = tokenParser;
        objectMapper = new ObjectMapper();
    }

    @GetMapping("/findallfollowers")
    public List<FollowsListEntity> allfollowers(){
        List<FollowsListEntity> followers = followerService.findAllFollowers();
        return followers;
    }

    @GetMapping(value = "/followerdetail", produces = "application/json")
    public String allfollowerdetail(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        tokenParser.checkToken(request);
        if(request.getAttribute("principal") == null) {
            response.setStatus(401);
            return "Not Authorized.";
        }
        Integer userId = ((Principal)request.getAttribute("principal")).getId();
        List<FollowerDetailDTO> followers = followerService.getAllFollowerDetail(userId);
        response.setStatus(201);

        return objectMapper.writeValueAsString(followers);
    }


    @PutMapping(value = "/movieScore", consumes = "application/json")
    public void scoreMovie(@RequestBody MovieReviewDTO reviewDTO,
                           HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        tokenParser.checkToken(request);
        if(request.getAttribute("principal") == null) {
            response.setStatus(401);
            return;
        }
        Integer userId = ((Principal)request.getAttribute("principal")).getId();
        System.out.println("Principal userId: " + userId);

        profileService.addToHistory(userId, reviewDTO.getImdbId());

        WatchHistoryEntity historyEntity = profileService.getHistoryEntity(userId, reviewDTO);

        response.setStatus(200);
    }

}
