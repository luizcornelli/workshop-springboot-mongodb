package com.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.workshopmongo.domain.Post;
import com.workshopmongo.domain.User;
import com.workshopmongo.dto.AuthorDTO;
import com.workshopmongo.repository.PostRepository;
import com.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria", "maria@gmail.com");
		User alex = new User(null, "Alex", "alex@gmail.com"); 
		User jose = new User(null, "José", "jose@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, jose));
		
		Post post1 = new Post(null, sdf.parse("21/09/2019"), "Viagem para São Paulo", "Uhul cheguei em São Paulo!", new AuthorDTO(maria)); 
		Post post2 = new Post(null, sdf.parse("23/10/2020"), "Almoçando no Shopping", "Saudades de comer esse Burguer King", new AuthorDTO(alex)); 
		Post post3 = new Post(null, sdf.parse("23/10/2020"), "Na Praia", "Tomando banho de mar coisa maravilhosa", new AuthorDTO(jose));
		Post post4 = new Post(null, sdf.parse("13/05/2020"), "Na quarentena", "Não aguento mais essa quarentena, partiu sair!!!", new AuthorDTO(jose));
		
		postRepository.saveAll(Arrays.asList(post1, post2, post3, post4)); 
		
		maria.getPosts().addAll(Arrays.asList(post1)); 
		alex.getPosts().addAll(Arrays.asList(post2));
		jose.getPosts().addAll(Arrays.asList(post3, post4)); 
		
		userRepository.saveAll(Arrays.asList(maria, alex, jose));
		
	}

}
