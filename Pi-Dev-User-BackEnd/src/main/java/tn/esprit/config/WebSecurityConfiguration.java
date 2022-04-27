package tn.esprit.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
/*	@Autowired
	private UserDetailService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);}
	@Override
	//the way will manage the httpSecurity
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/UserMng/register").permitAll()
		.antMatchers("/UserMng/admin/**").access("hasRole('ADMIN')")
		.antMatchers("/UserMng/Mng/**").access("hasRole('ADMIN') and hasRole('CLIENT')")
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic().and().csrf().disable();
		
		
		//on désactive csrf => csrf().disable(); car l'app est locale
		
	}*/
	
}
