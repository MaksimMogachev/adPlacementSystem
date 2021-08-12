package com.senlaCourses.adPlacementSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan(basePackages = "com.senlaCourses.adPlacementSystem")
@EnableTransactionManagement
public class ApplicationContextConfig {

  @Bean
  public EntityManagerFactory entityManagerFactory() {
    return Persistence.createEntityManagerFactory("");
  }

  @Bean
  public EntityManager entityManager() {
    return entityManagerFactory().createEntityManager();
  }

  @Bean(name = "transactionManager")
  public PlatformTransactionManager transactionManager() {
    JpaTransactionManager tm =
            new JpaTransactionManager();
    tm.setEntityManagerFactory(entityManagerFactory());
    return tm;
  }

  @Bean
  public static PropertySourcesPlaceholderConfigurer properties(){
    PropertySourcesPlaceholderConfigurer pspc
            = new PropertySourcesPlaceholderConfigurer();
    Resource[] resources = new ClassPathResource[] {new ClassPathResource("springSrc.properties")};
    pspc.setLocations(resources);
    pspc.setIgnoreUnresolvablePlaceholders(true);
    return pspc;
  }
}
