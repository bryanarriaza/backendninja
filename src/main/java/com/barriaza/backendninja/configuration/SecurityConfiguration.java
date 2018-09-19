package com.barriaza.backendninja.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userServiceImpl")
	private UserDetailsService userService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder _auth) throws Exception {
		_auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity _http) throws Exception {
		_http.authorizeRequests().antMatchers("/css/*", "/imgs/*").permitAll().anyRequest().authenticated().and()
				.formLogin().loginPage("/login").loginProcessingUrl("/logincheck").usernameParameter("username")
				.passwordParameter("password").defaultSuccessUrl("/loginsuccess").permitAll().and().logout()
				.logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll();
	}

}
