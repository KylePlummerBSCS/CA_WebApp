package com.revature.WebApp.utils;

import com.revature.WebApp.entities.APICallsEntity;
import com.revature.WebApp.repositories.ApiCallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.javatuples.Pair;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
public class APICallTracker {
    private ApiCallRepository apiCallRepo;
    /**
     * Map contains K=api name, V=pair. Pair contains: S=number, T=period.
     * Map contains elements which map an API name(K=String) to a pair(V=Pair)
     * describing the amount of allowed calls(S=Integer) in a timeframe(T=String).
     * So for instance: OMDb allows 1,000 free API calls per day and IMDb allows 200 per month.
     * <"OMDb", <1000, "day">>
     * <"IMDb", <200, "month">>
     */
    private Map<String, Pair<Integer, String>> apiLimits;


    @Autowired
    public APICallTracker(ApiCallRepository apiCallRepo){
        this.apiCallRepo = apiCallRepo;
        apiLimits = new HashMap<>();
        apiLimits.put("OMDb", new Pair<Integer, String>(1000, "day"));
        apiLimits.put("RapidMDb", new Pair<Integer, String>(1000, "day"));
        apiLimits.put("IMDb", new Pair<Integer, String>(200, "month"));
    }

    /**
     * Tracks API calls and determines if there are available free calls to that endpoint this timeframe.
     * Save the API call audit entry in the database, and then check to see if there are too many calls to that
     * endpoint. If max in a timeframe is exceeded, return false. Otherwise return true.
     * @param entity
     * @return
     */
    public boolean trackApiCall(APICallsEntity entity) {
        apiCallRepo.save(entity);
        String period = apiLimits.get(entity.getApi()).getValue1();
        Integer maxCalls = apiLimits.get(entity.getApi()).getValue0();

        switch(period) {
            case "day":
                if(maxCalls <= apiCallRepo.getApiCallsToday(entity.getApi(), LocalDate.now())) {
                    System.out.println("DEBUG: too many calls today");
                    return false;
                }
                break;

            case "month":
                if(maxCalls <= apiCallRepo.getApiCallsThisMonth(entity.getApi(), LocalDate.now().getMonthValue())) {
                    System.out.println("DEBUG: too many calls this month");
                    return false;
                }
                break;
            default:
                return false;
        }

        return true;
    }

    //TODO: could create some sort of maintenance method that would clear out older entries from the tracking table.
    //Maybe move them into an archive table instead if we want to keep an audit.

}
