package com.revature.WebApp.utils;

import com.revature.WebApp.entities.APICallsEntity;
import com.revature.WebApp.repositories.ApiCallRepository;
import org.javatuples.Pair;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.mockito.Mockito.*;

public class APICallTrackerTests {

    String apiDaily = "OMDb";
    String apiMonthly = "IMDb";

    APICallTracker sut;
    ApiCallRepository mockRepo;
    APICallsEntity mockEntityDaily;
    APICallsEntity mockEntityMonthly;


    @Before
    public void setUp() {
        mockRepo = mock(ApiCallRepository.class);

        mockEntityDaily = spy(new APICallsEntity());
        mockEntityDaily.setApi(apiDaily);

        mockEntityMonthly = spy(new APICallsEntity());
        mockEntityMonthly.setApi(apiMonthly);

        sut = new APICallTracker(mockRepo);
    }

    @After
    public void tearDown() {
        mockEntityDaily = null;
        mockEntityMonthly = null;
        mockRepo = null;
        sut = null;
    }

    @Test
    public void testTrackApiCallWhenValid() {

        //Arrange
        boolean testBoolean1000Daily;
        boolean testBoolean200Monthly;

        when(mockRepo.getApiCallsToday(anyString(), any())).thenReturn(999);
        when(mockRepo.getApiCallsThisMonth(anyString(),any())).thenReturn(199);

        //Act
        testBoolean1000Daily = sut.trackApiCall(mockEntityDaily);
        testBoolean200Monthly = sut.trackApiCall(mockEntityMonthly);

        //Assert
        verify(mockRepo,times(2)).save(any());
        Assert.assertTrue(testBoolean1000Daily);
        Assert.assertTrue(testBoolean200Monthly);
    }

    @Test
    public void testTrackApiCallWhenOverLimit() {

        //Arrange
        boolean testBoolean1000Daily;
        boolean testBoolean200Monthly;

        when(mockRepo.getApiCallsToday(anyString(), any())).thenReturn(1001);
        when(mockRepo.getApiCallsThisMonth(anyString(), any())).thenReturn(201);

        //Act
        testBoolean1000Daily = sut.trackApiCall(mockEntityDaily);
        testBoolean200Monthly = sut.trackApiCall(mockEntityMonthly);


        //Assert
        verify(mockRepo,times(2)).save(any());
        Assert.assertFalse(testBoolean1000Daily);
        Assert.assertFalse(testBoolean200Monthly);
    }
}
