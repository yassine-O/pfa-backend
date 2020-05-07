package org.sid.security;

import java.util.ArrayList;
import java.util.List;

import org.sid.dao.UserDao;
import org.sid.model.ResponsableRH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailService implements UserDetailsService {
	@Autowired
	UserDao userdao;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		ResponsableRH user=userdao.findByEmail(email);
		  List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

	        list.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
		return new User(user.getEmail(),user.getPassword(),list);
		
	}

}
