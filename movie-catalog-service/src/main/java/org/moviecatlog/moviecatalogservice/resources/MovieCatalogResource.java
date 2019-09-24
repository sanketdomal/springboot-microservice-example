package org.moviecatlog.moviecatalogservice.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.moviecatlog.moviecatalogservice.models.CatalogItem;
import org.moviecatlog.moviecatalogservice.models.Movie;
import org.moviecatlog.moviecatalogservice.models.Rating;
import org.moviecatlog.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	//restTemplate will be deprecated soon
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		UserRating ratings = restTemplate.getForObject("http://rating-data-service/ratingdata/users/" + userId, UserRating.class);	

		return ratings.getUserRatings().stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movies/" + rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(), "desc", rating.getRating());
		}).collect(Collectors.toList());
	}
	
	//using web-client for communication(reactive way )	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@RequestMapping("/webclient/{userId}")
	public List<CatalogItem> getCatalog1(@PathVariable("userId") String userId) {

		List<Rating> ratings = new ArrayList<Rating>();
		ratings.add(new Rating("dab", 4));
		ratings.add(new Rating("trans", 3));

		return ratings.stream().map(rating -> {
			
			Movie movie=webClientBuilder.build()
			.get()
			.uri("http://localhost:8082/movies/" + rating.getMovieId())
			.retrieve()
			.bodyToMono(Movie.class)
			.block();			
			return new CatalogItem(movie.getName(), "desc", rating.getRating());
		}).collect(Collectors.toList());
	}
	

//	@RequestMapping("/{userId}")
//	public List<CatalogItem> getCatalog(@PathVariable("userId")String userId) {
//		
//		List<CatalogItem> catalogItems = new ArrayList<CatalogItem>();
//		catalogItems.add(new CatalogItem("Transformer", "Transformer", 3));
//		catalogItems.add(new CatalogItem("dabangg", "dabangg desc", 4));
//		catalogItems.add(new CatalogItem("Avengers", "avengers desc", 4));
//		return catalogItems;
//	}

}
