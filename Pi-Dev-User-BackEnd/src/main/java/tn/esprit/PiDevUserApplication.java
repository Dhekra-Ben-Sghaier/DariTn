package tn.esprit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;



@SpringBootApplication
/*(exclude= {UserDetailsServiceAutoConfiguration.class})
@ComponentScan("package org.springframework.security.authentication")
*/
@EnableSwagger2
public class PiDevUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(PiDevUserApplication.class, args);
	}

}
