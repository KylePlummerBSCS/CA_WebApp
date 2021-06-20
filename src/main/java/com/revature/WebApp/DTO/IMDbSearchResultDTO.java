package com.revature.WebApp.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;
@JsonIgnoreProperties
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class IMDbSearchResultDTO {
    
    private List<IMDbElementDTO> search;
    private String totalResult;
    private String results;
    private String status;
    private String statusMessage;
    
    public IMDbSearchResultDTO(){
    
    }
  
    public List<IMDbElementDTO> getSearch() {
        return search;
    }
    
    public void setSearch(List<IMDbElementDTO> search) {
        this.search = search;
    }
    
    public String getTotalResult() {
        return totalResult;
    }
    
    public void setTotalResult(String totalResult) {
        this.totalResult = totalResult;
    }
    
    public String getResults() {
        return results;
    }
    
    public void setResults(String results) {
        this.results = results;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getStatusMessage() {
        return statusMessage;
    }
    
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
    
    
    @Override
    public String toString() {
        return "IMDbSearchResultDTO{" +
               "search=" + search +
               ", totalResult='" + totalResult + '\'' +
               ", results='" + results + '\'' +
               ", status='" + status + '\'' +
               ", statusMessage='" + statusMessage + '\'' +
               '}';
    }
    
}
