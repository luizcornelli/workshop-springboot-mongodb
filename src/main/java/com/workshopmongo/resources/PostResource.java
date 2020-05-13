package com.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.workshopmongo.domain.Post;
import com.workshopmongo.resources.utils.URL;
import com.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	
	@Autowired
	private PostService postService; 
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id) {
		
		Post post = postService.findById(id); 
		
		return ResponseEntity.ok().body(post); 
	}
	
	@RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {

		String textDecoded = URL.decodeParam(text);
		List<Post> posts = postService.findByTitle(textDecoded); 

		return ResponseEntity.ok().body(posts);
	}
	
	@RequestMapping(value = "/fullsearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate) {

		String textDecoded = URL.decodeParam(text);
		Date minDateDecoded = URL.convertDate(minDate, new Date(0L));
		Date maxDateDecoded = URL.convertDate(maxDate, new Date());

		List<Post> posts = postService.fullSearch(textDecoded, minDateDecoded, maxDateDecoded); 

		return ResponseEntity.ok().body(posts);
	}
}
