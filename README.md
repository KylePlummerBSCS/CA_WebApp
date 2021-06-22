# Cineholics Anonymous
A cinephile's best friend, Cineholics Anonymous, is a movie tracking site with robust social features. 
Users can track a history of films watched, as well as keep a wish list of future viewings. Cineholics Anonymous allows users to keep a list of other 
cinephiles as friends, as well as share their histories and wish lists. Additionally, users can rate and review the films they've seen, or search for 
new films. Cineholics Anonymous populates data from the Internet Movie Database to keep the library up-to-date.

### External APIs
CA searches for titles using the OpenMDb API, which returns titles and IDs. These IDs match the unique IDs in the IMDb and RapidAPI MDb APIs.

 https://www.omdbapi.com/

 CA gets movie details from RapidAPI's Movie Database. This alternative to IMDb offers almost the same details and allows for 1,000 free calls per day.
RapidAPI MDb lacks the detailed search features of IMDb API.
 
 https://rapidapi.com/rapidapi/api/movie-database-imdb-alternative

CA utilizes the detailed search features of IMDb API to access popular titles, and titles by genre or cast. IMDb API only allows for 200 calls per day.
 
 https://rapidapi.com/rapidapi/api/movie-database-imdb-alternative
 
 
