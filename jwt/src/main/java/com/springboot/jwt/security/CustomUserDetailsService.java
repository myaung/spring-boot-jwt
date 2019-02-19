package com.springboot.jwt.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.jwt.entity.User;
import com.springboot.jwt.exception.ResourceNotFoundException;
import com.springboot.jwt.repository.UserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String usernameOremail) throws UsernameNotFoundException {
		User user = userRepository.findByUsernameOrEmail(usernameOremail, usernameOremail).
				orElseThrow(()->new UsernameNotFoundException("Username  not found :"+usernameOremail));
		return UserPrincipal.create(user);
	}
	
	public UserDetails loadByUserId(long id){
		User user = userRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("User","Id" , id) );
		return UserPrincipal.create(user);
	}

}
