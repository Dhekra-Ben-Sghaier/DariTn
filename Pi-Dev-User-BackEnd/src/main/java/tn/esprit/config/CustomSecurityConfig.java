package tn.esprit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/*@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
*/
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    private final String uri = "/SpringPiDariTN/";

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().httpStrictTransportSecurity().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        // Authorize sub-folders permissions
        http.antMatcher(uri).authorizeRequests().anyRequest().permitAll();
        http.csrf().disable()
        .authorizeRequests()
         .antMatchers("/SpringPiDariTN/**").authenticated()
         .anyRequest().permitAll()
         .and()
       .httpBasic().and()
       .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
    	
    }
}
