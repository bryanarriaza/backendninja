package com.barriaza.backendninja.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.barriaza.backendninja.entity.UserRole;
import com.barriaza.backendninja.repository.UserRepository;

@Service("userServiceImpl")
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String _username) throws UsernameNotFoundException {
		com.barriaza.backendninja.entity.User user = userRepository.findByUsername(_username);
		List<GrantedAuthority> authorities = buildAuthorities(user.getUserRole());
		return buildUser(user, authorities);
	}

	private User buildUser(com.barriaza.backendninja.entity.User user, List<GrantedAuthority> _authorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, _authorities);
	}

	private List<GrantedAuthority> buildAuthorities(Set<UserRole> _userRole) {
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		for (UserRole userRole : _userRole) {
			auths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		return new ArrayList<GrantedAuthority>(auths);
	}

}
