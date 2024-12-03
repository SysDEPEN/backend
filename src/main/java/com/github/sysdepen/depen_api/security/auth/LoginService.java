//AuthenticationService.java
package com.github.sysdepen.depen_api.security.auth;

import com.github.sysdepen.depen_api.security.config.JwtServiceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
public class LoginService {
	
	@Autowired
	private LoginRepository repository;
	@Autowired
	private JwtServiceGenerator jwtService;
	
	
	@Autowired
	private AuthenticationManager authenticationManager;


	public String logar(Login login) {
		
		//AUTENTICA
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						login.getDocument(),
						login.getPassword()
						)
				);
		
		Usuario user = repository.findByDocument(login.getDocument()).get();
		String jwtToken = jwtService.generateToken(user);
		
		return jwtToken;
	}

}
