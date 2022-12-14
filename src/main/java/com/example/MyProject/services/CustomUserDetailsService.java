package com.example.MyProject.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.MyProject.models.Role;
import com.example.MyProject.models.User;
import com.example.MyProject.repositories.RoleRepository;
import com.example.MyProject.repositories.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired	
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired 
	private BCryptPasswordEncoder bCryptPassowrdEncoder;
	
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public void saveUser(User user) {
		user.setPassword( bCryptPassowrdEncoder.encode(user.getPassword()));
		user.setEnabled(true);
		Role userRole = roleRepository.findByRole("ADMIN");
		user.setRoles(new HashSet<>(Arrays.asList(userRole)));
		userRepository.save(user);
	}
	@Override 
	public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(email);
		if (user != null) {
			List<GrantedAuthority> authorities = getUserAuthority (user.getRoles());
			return buildUserForAuthentication(user ,authorities);
		} else {
			throw new UsernameNotFoundException ("Username not found");
			
		}
		
	}

	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
		
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}

	private List<GrantedAuthority>  getUserAuthority(Set<Role> userRoles) {
		Set<GrantedAuthority>roles = new HashSet<>();
		userRoles.forEach((role) -> {
			roles.add(new SimpleGrantedAuthority(role.getRole()));
		});
		
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
		return grantedAuthorities;
	}
	
	
	
}
