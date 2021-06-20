package com.revature.WebApp.services;

import com.revature.WebApp.APIAccess.OMDbAPI;
import com.revature.WebApp.DTO.OMDbSearchResultsDTO;
import com.revature.WebApp.entities.MovieDetailsEntity;
import com.revature.WebApp.repositories.MovieDetailsRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class MovieDetailsServiceTests {

    String imdbId = "tt0298148";
    private MovieDetailsService sut;
    private MovieDetailsRepository mockMovieRepo;
    private OMDbAPI mockOmdbApi;
    private MovieDetailsEntity mockMovieDetailsEntity;
    private OMDbSearchResultsDTO mockOmdbSearchResultsDto;

    @Before
    public void setUp() {
        mockMovieRepo = mock(MovieDetailsRepository.class);
        mockOmdbApi = mock(OMDbAPI.class);
        mockMovieDetailsEntity = mock(MovieDetailsEntity.class);
        mockOmdbSearchResultsDto = mock(OMDbSearchResultsDTO.class);
        sut = new MovieDetailsService(mockMovieRepo, /*history here*/mockOmdbApi);
    }

    @After
    public void tearDown() {
        sut = null;
        mockMovieRepo = null;
        mockOmdbApi = null;
        mockMovieDetailsEntity = null;
    }

    @Test
    public void testGetMovieDetailsByImdbIdFromDatabase() throws IOException {
        //Arrange
        Object testObject;
        when(mockMovieRepo.findByImdbId(anyString())).thenReturn(mockMovieDetailsEntity);

        //Act
        testObject = sut.getMovieDetailsByImdbId(anyString());

        //Assert
        verify(mockMovieRepo, times(1)).findByImdbId(anyString());
        verify(mockOmdbApi, times(0)).searchByImdbId(anyString());
        verify(mockMovieRepo, times(0)).save(any());
        Assert.assertNotNull(testObject);
    }

    @Test//(expected = NumberFormatException.class)
    public void testGetMovieDetailsByImdbIdFromApi() throws IOException {
        //Arrange
        Object testObject;
        mockMovieDetailsEntity = null;
        when(mockOmdbSearchResultsDto.getYear()).thenReturn("2004");
        when(mockMovieRepo.findByImdbId(imdbId)).thenReturn(mockMovieDetailsEntity);
        when(mockOmdbApi.searchByImdbId(imdbId)).thenReturn(mockOmdbSearchResultsDto);

        //Act
        testObject = sut.getMovieDetailsByImdbId(imdbId);

        //Assert
        verify(mockMovieRepo, times(1)).findByImdbId(anyString());
        verify(mockOmdbApi, times(1)).searchByImdbId(anyString());
        verify(mockMovieRepo, times(1)).save(any());
        Assert.assertNotNull(testObject);
    }
}
