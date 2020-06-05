package org.sid.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.sid.model.Utilisateur;
import org.sid.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private AccountService accountService;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilisateur user = accountService.findUserByEmail(username);
		if (user==null) throw new UsernameNotFoundException(username);
		Collection<GrantedAuthority> Authorities = new ArrayList<GrantedAuthority>();
		Authorities.add(new SimpleGrantedAuthority(user.getRole()));
		return new User(user.getEmail(), user.getPassword(), Authorities);
	}

}
