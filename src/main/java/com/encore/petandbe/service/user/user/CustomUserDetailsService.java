package com.encore.petandbe.service.user.user;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.encore.petandbe.model.user.user.CustomUserDetails;
import com.encore.petandbe.model.user.user.Role;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Collection<GrantedAuthority> roles = new ArrayList<>();

		roles.add((GrantedAuthority)Role.USER::getValue);

		return new CustomUserDetails(username, roles);
	}
}
