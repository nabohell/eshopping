package com.aize.assignment.servcies;

import com.aize.assignment.erros.UserNotFoundException;
import com.aize.assignment.models.User;
import com.aize.assignment.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {
	@Autowired
	UserRepo userRepo;

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findById(username).orElseThrow(UserNotFoundException::new);
	}

}