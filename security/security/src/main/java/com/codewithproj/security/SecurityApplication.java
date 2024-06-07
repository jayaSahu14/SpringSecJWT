package com.codewithproj.security;

import com.codewithproj.security.entity.Role;
import com.codewithproj.security.entity.User;
import com.codewithproj.security.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SecurityApplication implements CommandLineRunner {

	@Autowired
	UserRepo userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User adminAccount = userRepository.findByRole(Role.ADMIN);
		if( null==adminAccount){
			User user = new User();
			user.setEmail("admin@gmail.com");
			user.setFname("admin");
			user.setLname("admin");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));


		}

	}
}
