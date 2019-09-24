package org.moviecatlog.ratingdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.moviecatlog.ratingdataservice.models.Rating;
import org.moviecatlog.ratingdataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingdata")
public class RatingDataResource {

	@RequestMapping("/{movieId}")
	public Rating getrating(@PathVariable("movieId") String movieId) {

		Rating rating1 = new Rating("dab", 4);
		return rating1;
	}
	
	@RequestMapping("/users/{userId}")
	public UserRating getRating(@PathVariable("userId") String movieId) {
		 List<Rating> ratings= Arrays.asList(
			new Rating("dab", 4),
			new Rating("trans", 3)			
		);
		 
		 UserRating userRating = new UserRating(ratings);		 
		 return userRating;
	}

}
