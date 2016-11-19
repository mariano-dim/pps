package edu.proyectofinal.integradorrs.configurations;
/*

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import edu.proyectofinal.integradorrs.services.usuario.impl.UsuarioServiceImpl;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

//@Configuration
//@EnableWebSecurity
//@EnableWebMvc Security
//@ComponentScan(basePackageClasses=UsuarioServiceImpl.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /* 
	@Override
    protected void configure(HttpSecurity http) throws Exception {
    	/*http.
  
		
		
            authorizeRequests().antMatchers("/", "/home").permitAll()
            .and()
            .csrf().disable().authorizeRequests().antMatchers("/connect**").authenticated()
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/login").permitAll()
            .and()
            .logout().permitAll();
		
	
		
    	// agregar settings  : http://stackoverflow.com/questions/34295430/http-status-403-expected-csrf-token-not-found-has-your-session-expired
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
       
    }
    /*
    @Autowired
    public void configureGlobal(DataSource dataSource, AuthenticationManagerBuilder auth) throws Exception {
        auth
        	.jdbcAuthentication()
            	.dataSource(dataSource)
            	.withDefaultSchema()
                .withUser("user").password("password").roles("USER");
       
    }
       
}*/