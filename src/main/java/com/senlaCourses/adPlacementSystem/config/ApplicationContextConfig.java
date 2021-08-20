package com.senlaCourses.adPlacementSystem.config;

import java.io.IOException;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.velocity.VelocityEngineFactory;

/**
 * Spring application context.
 */
@Configuration
@ComponentScan(basePackages = "com.senlaCourses.adPlacementSystem")
@EnableTransactionManagement(proxyTargetClass = true)
public class ApplicationContextConfig {

  /**
   * Configs mail sender.
   *
   * @return configured mail sender.
   */
  @Bean
  public JavaMailSender getMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    mailSender.setHost("smtp.gmail.com");
    mailSender.setPort(587);
    mailSender.setUsername("your-email");
    mailSender.setPassword("your-password");

    Properties javaMailProperties = new Properties();
    javaMailProperties.put("mail.smtp.starttls.enable", "true");
    javaMailProperties.put("mail.smtp.auth", "true");
    javaMailProperties.put("mail.transport.protocol", "smtp");
    javaMailProperties.put("mail.debug", "true");

    mailSender.setJavaMailProperties(javaMailProperties);
    return mailSender;
  }

  /**
   * Configs VelocityEngine.
   *
   * @return configured VelocityEngine.
   */
  @Bean
  public VelocityEngine getVelocityEngine() throws VelocityException, IOException {
    VelocityEngineFactory velocityEngineFactory = new VelocityEngineFactory();
    Properties props = new Properties();
    props.put("resource.loader", "class");
    props.put("class.resource.loader.class",
        "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

    velocityEngineFactory.setVelocityProperties(props);
    return velocityEngineFactory.createVelocityEngine();
  }

  @Bean
  @Scope("singleton")
  public EntityManager entityManager() {
    return entityManagerFactory().createEntityManager();
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
}
