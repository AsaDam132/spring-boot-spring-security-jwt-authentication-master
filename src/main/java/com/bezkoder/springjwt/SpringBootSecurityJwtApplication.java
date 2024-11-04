package com.bezkoder.springjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("org.springframework.data.jpa.repository.JpaRepository")
@ComponentScan(basePackages = "com.bezkoder.springjwt.security.services.CommentService")
 class getSpringBootSecurityJwtApplication {

	public static void main(String[] args) {


		SpringApplication.run(getSpringBootSecurityJwtApplication.class, args);
	}


}
