package com.incamp.mhs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@ComponentScan("com.incamp.mhs")
@EnableWebMvc
@PropertySource("classpath:/application.properties")
public class AppConfig {


    private final Environment env;

    public AppConfig(Environment env) {
        this.env = env;
    }


    @Bean
    public static PropertyPlaceholderConfigurer properties() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        Resource[] resources = new ClassPathResource[]
                {new ClassPathResource("application.properties")};
        ppc.setLocations(resources);
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }

    @Bean
    public DriverManagerDataSource dataSource() {
        String dbUrl = env.getProperty("jdbc.url");
        String dbUser = env.getProperty("jdbc.username");
        String dbPass = env.getProperty("jdbc.password");
        String dbDriverClassName = env.getProperty("jdbc.driver");
        DriverManagerDataSource dataSource = new DriverManagerDataSource(dbUrl, dbUser, dbPass);
        dataSource.setDriverClassName(dbDriverClassName);
        return dataSource;
    }
}