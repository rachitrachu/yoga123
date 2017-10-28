package com.yugma.school.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import com.yugma.school.CorsFilter;

@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private Environment env;

    @Override
    public void configure(final ResourceServerSecurityConfigurer config) {
        config.tokenServices(tokenServices());
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }

    // JDBC token store configuration

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.pass"));
        return dataSource;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

    	http.
		csrf().disable()
		.addFilterBefore(new CorsFilter(),ChannelProcessingFilter.class)
		.requestMatchers()
		.antMatchers("/parent/**")
		.antMatchers("/principal/**")
		.antMatchers("/vice-principal/**")
		.antMatchers("/management/**")
		.antMatchers("/coordinator/**")
		.antMatchers("/class-teacher/**")
		.antMatchers("/teacher/**")
		.antMatchers("/director/**")
		.and().authorizeRequests()
		.antMatchers("/parent/**").access("hasRole('PARENT')")
		.antMatchers("/principal/**").access("hasRole('PRINCIPAL')")
		.antMatchers("/vice-principal/**").access("hasRole('VICE_PRINCIPAL')")
		.antMatchers("/management/**").access("hasRole('MANAGEMENT')")
		.antMatchers("/director/**").access("hasRole('DIRECTOR')")
		.antMatchers("/class-teacher/**").access("hasRole('CLASS_TEACHER')")
		.antMatchers("/coordinator/**").access("hasRole('COORDINATOR')")
		.antMatchers("/teacher/**").access("hasRole('TEACHER')")
		.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());

    }
}
