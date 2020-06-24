package com.techdev.sdg.Authentication;

import com.techdev.sdg.Entity.Entity;
import com.techdev.sdg.PrivateSector.PrivateSector;
import com.techdev.sdg.PrivateSector.PrivateSectorRepository;
import com.techdev.sdg.NGO.NGO;
import com.techdev.sdg.NGO.NGORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private NGORepository ngoUser;

	@Autowired
	private PrivateSectorRepository psUser;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			PrivateSector user = psUser.findByName(username);
			if (user == null) {
				throw new UsernameNotFoundException("User not found with username: " + username);
			}
			return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
					new ArrayList<>());
		}catch(Exception e)
		{
			NGO user = ngoUser.findByName(username);
			if (user == null) {
				throw new UsernameNotFoundException("User not found with username: " + username);
			}
			return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
					new ArrayList<>());
		}
	}

	public Entity loadUserObject(String username) throws UsernameNotFoundException {
		Entity entity;
		try {
			entity = psUser.findByName(username);
			if (entity == null) {
				throw new UsernameNotFoundException("User not found with username: " + username);
			}
		} catch (Exception e) {
			entity = ngoUser.findByName(username);
			if (entity == null) {
				throw new UsernameNotFoundException("User not found with username: " + username);
			}
		}
		return entity;
	}
}