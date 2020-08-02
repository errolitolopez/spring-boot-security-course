package com.example.springbootsecuritycourse.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.springbootsecuritycourse.repository.UserRepository;
import com.example.springbootsecuritycourse.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomUserDetailsService customUserDetailsService;
	private final PasswordEncoder bCryptPasswordEncoder;
	@SuppressWarnings("unused")
	private final PasswordEncoder plainTextPasswordEncoder;

	@Autowired
	public SecurityConfig(CustomUserDetailsService customUserDetailsService,
			PasswordEncoder bCryptPasswordEncoder,
			PasswordEncoder plainTextPasswordEncoder) {
		this.customUserDetailsService = customUserDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.plainTextPasswordEncoder = plainTextPasswordEncoder;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				.antMatchers("/",
						"index",
						"/css/*",
						"/js/*",
						"/v2/api-docs",
						"/configuration/ui",
						"/swagger-resources/**",
						"/configuration/security",
						"/swagger-ui.html",
						"/webjars/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/console/**").hasRole("ADMIN")
				.anyRequest()
				.authenticated();

		http.formLogin()
				.loginPage("/login").permitAll()
				.defaultSuccessUrl("/dashboard", true)
				.usernameParameter("username")
				.passwordParameter("password")
				.and()
				.rememberMe()
				.rememberMeParameter("remember-me")
				.tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
				.key("somethingverysecured")
				.and()
				.logout()
				.logoutUrl("/logout")
				.clearAuthentication(true)
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID", "remember-me")
				.logoutSuccessUrl("/login");

		http.headers().frameOptions().disable();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService)
				.passwordEncoder(bCryptPasswordEncoder);
	}

}
