package com.revature.WebApp.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.WebApp.APIAccess.IMDbAPI;
import com.revature.WebApp.DTO.IMDbSearchResultDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class IMDbController {
    
    private final ObjectMapper json;
    private final IMDbAPI apiAccess;
    
    
    public IMDbController(IMDbAPI api){
        apiAccess = api;
        json = new ObjectMapper();
    }
    
    
    @GetMapping(value="/imdbSearch", produces = "application/json")
    public String popularitySearch(HttpServletResponse response) throws IOException{
        IMDbSearchResultDTO searchResultDTO = apiAccess.searchByPopularity();
        
        if(searchResultDTO==null){
            response.setStatus(503);
            return "No more calls allowed to IMDb in this time period";
        }
        if(searchResultDTO.getStatus().equals("False")){
            response.setStatus(404);
            return "No Searches where found for that year";
        }
        
        response.setStatus(200);
        return json.writeValueAsString(searchResultDTO);
    }
    
}
