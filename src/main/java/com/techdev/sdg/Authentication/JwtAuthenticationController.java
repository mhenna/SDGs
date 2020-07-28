package com.techdev.sdg.Authentication;

import com.techdev.sdg.Authentication.config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = Router.AUTHENTICATE, method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {
		try {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
			final Map<String, Object> user = userDetailsService.loadUserObject(authenticationRequest.getUsername());

			if (user.containsKey("isApproved")) {
				if (!Boolean.parseBoolean(user.get("isApproved").toString()))
					return new ResponseEntity<>("Your registration has not been approved yet, " +
							"once you are approved, an email will be sent to you", HttpStatus.UNAUTHORIZED);
			}
			final String token = jwtTokenUtil.generateToken(userDetails, user);

			return ResponseEntity.ok(new JwtResponse(token));
		} catch (Exception e) {
			return new ResponseEntity<>("Unexpected error occured: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
