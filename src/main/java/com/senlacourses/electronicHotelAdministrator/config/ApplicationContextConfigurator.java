package com.senlacourses.electronicHotelAdministrator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.Persistence;

@Configuration
@ComponentScan(basePackages = "com.senlacourses.electronicHotelAdministrator")
@EnableTransactionManagement(proxyTargetClass = true)
public class ApplicationContextConfigurator {

  @Bean(name = "transactionManager")
  public PlatformTransactionManager transactionManager() {
    JpaTransactionManager tm =
            new JpaTransactionManager();
    tm.setEntityManagerFactory(Persistence.createEntityManagerFactory("eha"));
    return tm;
  }

  @Bean
  public static PropertySourcesPlaceholderConfigurer properties(){
    PropertySourcesPlaceholderConfigurer pspc
            = new PropertySourcesPlaceholderConfigurer();
    Resource[] resources = new ClassPathResource[] {new ClassPathResource("springSources.properties")};
    pspc.setLocations(resources);
    pspc.setIgnoreUnresolvablePlaceholders(true);
    return pspc;
  }
}
