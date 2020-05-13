package com.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshopmongo.domain.Post;
import com.workshopmongo.domain.User;
import com.workshopmongo.repository.PostRepository;
import com.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository; 
	
	public Post findById(String id) {
		
		Optional<Post> obj = postRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));	
	}
	
	public List<Post> findByTitle(String text) {
		
		return postRepository.searchTitle(text);
		
		//return postRepository.findByTitleContainingIgnoreCase(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		
		// Adicionando um dia a dataMaxima pois queremos encontrar o texto até o final do dia (24hrs).
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		
		return postRepository.fullSearch(text, minDate, maxDate); 
	}
}
