package org.moviecatlog.movieinfoservice.resources;

import java.util.ArrayList;
import java.util.List;

import org.moviecatlog.movieinfoservice.models.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieResource {

	@RequestMapping("/{movieId}")
	public Movie getMovie(@PathVariable("movieId") String movieId) {

		
		List<Movie> movies = new ArrayList<Movie>();
		movies.add(new Movie("dab", "Dabbang"));
		movies.add(new Movie("trans", "Transformer"));
		
		for(Movie m:movies) {
			if(m.getMovieId().equals(movieId)) {
				return m;
			}
		}
		return null;
	}

}
