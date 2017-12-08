package com.modern.codes.lime;

import com.modern.codes.lime.config.CustomUserDetails;
import com.modern.codes.lime.dao.IUserDAO;
import com.modern.codes.lime.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.util.Arrays;

@SpringBootApplication
public class LimeApplication {
	public static void main(String[] args) {
		SpringApplication.run(LimeApplication.class, args);
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, IUserDAO dao, PasswordEncoder encoder) throws Exception {
		builder.userDetailsService(userDetailsService(dao)).passwordEncoder(encoder);
	}


	private UserDetailsService userDetailsService(final IUserDAO dao) {
		return username -> new CustomUserDetails(dao.findByUsername(username));
	}

}
