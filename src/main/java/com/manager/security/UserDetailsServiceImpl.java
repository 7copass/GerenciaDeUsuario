package com.manager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manager.model.UserSystem;
import com.manager.repositories.UserSystemRepository;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserSystemRepository userSystemRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserSystem userSystem = userSystemRepository.findByLogin(username);
		if(userSystem == null) {
			throw new UsernameNotFoundException("User not Found");
		}
		return new User(userSystem.getLogin(), userSystem.getPassword(), true, true, true, true, userSystem.getAuthorities()) ;
	}

} 
