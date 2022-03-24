package br.com.caelum.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.caelum.auth.util.AuthUtils;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		return super.userDetailsService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.passwordEncoder(passwordEncoder())
				.withUser("restaurante1").password(passwordEncoder().encode("password1")).roles(AuthUtils.ROLE_RESTAURANTE).and()
				.withUser("cliente1").password(passwordEncoder().encode("password1")).roles(AuthUtils.ROLE_CLIENTE).and()
				.withUser("admin1").password(passwordEncoder().encode("password1")).roles(AuthUtils.ROLE_ADMINISTRATIVO, AuthUtils.ROLE_RESTAURANTE, AuthUtils.ROLE_CLIENTE);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf()
				.disable()
			.authorizeRequests()
				.antMatchers("/oauth/token").permitAll()
				.anyRequest().authenticated()
				.and().httpBasic();
	}

}
