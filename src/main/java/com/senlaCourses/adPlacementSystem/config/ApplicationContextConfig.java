package com.senlaCourses.adPlacementSystem.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring application context.
 */
@Configuration
@ComponentScan(basePackages = "com.senlaCourses.adPlacementSystem")
@EnableTransactionManagement(proxyTargetClass = true)
public class ApplicationContextConfig {

  @Bean
  @Scope("singleton")
  public EntityManager entityManager() {
    return entityManagerFactory().createEntityManager();
  }

  /**
   * Creates DataSource.
   * Bean ONLY for tests.
   *
   * @return configured DataSource.
   */
  @Bean
  public DataSource dataSource() {
    PGSimpleDataSource ds = new PGSimpleDataSource();
    ds.setServerName("127.0.0.1:5432");
    ds.setDatabaseName("postgres");
    ds.setUser("postgres");
    ds.setPassword("1234");
    return ds;
  }

  @Bean
  @Scope("singleton")
  public EntityManagerFactory entityManagerFactory() {
    return Persistence.createEntityManagerFactory("aps");
  }

  /**
   * Creates transaction manager of entity manager.
   *
   * @return bean of transaction manager.
   */
  @Bean(name = "transactionManager")
  public PlatformTransactionManager transactionManager() {
    JpaTransactionManager tm = new JpaTransactionManager();
    tm.setEntityManagerFactory(entityManagerFactory());
    return tm;
  }

  /**
   * Sets up work with the properties for spring.
   *
   * @return configured PropertySourcesPlaceholderConfigurer bean.
   */
  @Bean
  public static PropertySourcesPlaceholderConfigurer properties() {
    PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
    Resource[] resources = new ClassPathResource[] {new ClassPathResource("springSrc.properties")};
    pspc.setLocations(resources);
    pspc.setIgnoreUnresolvablePlaceholders(true);
    return pspc;
  }
}
