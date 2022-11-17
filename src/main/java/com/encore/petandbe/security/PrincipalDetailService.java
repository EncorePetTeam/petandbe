package com.encore.petandbe.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.encore.petandbe.exception.NonExistResourceException;
import com.encore.petandbe.repository.AccountRepository;
import com.encore.petandbe.security.dto.UserAccount;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

	private final AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByUsername(username)
			.orElseThrow(() -> new NonExistResourceException("해당 username을 갖는 Account를 찾을 수 없습니다."));
		return new UserAccount(account);
	}
}
