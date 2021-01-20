# Flixer
Flixer is an app that allows users to browse movies from the [The Movie Database API](http://docs.themoviedb.apiary.io/#).

### User Stories

- User can view a list of movies (title, poster image, and overview) currently playing in theaters from the Movie Database API.
- Expose details of movie (ratings using RatingBar, popularity, and synopsis) in a separate activity.
- Allow video posts to be played in full-screen using the YouTubePlayerView.

- Trailers for popular movies are played automatically when the movie is selected (1 point).
  - When clicking on a popular movie (i.e. a movie voted for more than 5 stars) the video should be played immediately.
  - Less popular videos rely on the detailed page should show an image preview that can initiate playing a YouTube video.
- Add a play icon overlay to popular movies to indicate that the movie can be played (1 point).
- Apply the popular ButterKnife annotation(deprecated, switched to databinding)  library to reduce view boilerplate. (1 point)
- Add a rounded corners for the images using the Glide transformations. (1 point)

- Views should be responsive for both landscape/portrait mode.
   - In portrait mode, the poster image, title, and movie overview is shown.
   - In landscape mode, the rotated alternate layout should use the backdrop image instead and show the title and movie overview to the right of it.

- Display a nice default for each image during loading
- Improved the user interface by experimenting with styling and coloring.
- For popular movies (i.e. a movie voted for more than 5 stars), the full backdrop image is displayed. Otherwise, a poster image, the movie title, and overview is listed. Use Heterogenous RecyclerViews and use different ViewHolder layout files for popular movies and less popular ones.



#### Portrait
<img src="walkthroughs/portrait_flixer2.gif" width=250><br>
<img src="walkthroughs/portrait_flixer1.gif" width=250><br>

#### Landscape
<img src="walkthroughs/landscape_flixer2.gif" height=250><br>
<img src="walkthroughs/landscape_flixer1.gif" height=250><br>


### Notes
 - While I was able to add data binding to remove some boilerplate code.  I have yet to figure out how to use databinding with the heterogenous recycler view, as it is a bit more complicated, and relies on multiple viewholders and bind functions.
 - Still having difficulty working with the Glide placeholder feature.  Getting the placeholder to be the correct size has been a source of difficulty. My current workaround involves bringing my placeholder image into a photo editor and manually setting its pixel-specific dimentions.  This may create a problem if I decide to resize the ImageView later on.
 - Struggling with themeing the actionbar.  May have to create a custom toolbar?

### Open-source libraries used

- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Androids
