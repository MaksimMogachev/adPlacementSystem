package com.senlacourses.electronicHotelAdministrator;

import com.senlacourses.electronicHotelAdministrator.dao.*;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelResident;
import com.senlacourses.electronicHotelAdministrator.domain.model.HotelRoom;
import com.senlacourses.electronicHotelAdministrator.domain.model.RegistrationCard;
import com.senlacourses.electronicHotelAdministrator.domain.model.Service;
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

import javax.persistence.Persistence;

@Configuration
@ComponentScan
@EnableTransactionManagement(proxyTargetClass = true)
public class ApplicationContextConfigurator {

  @Bean
  @Scope("singleton")
  public IGenericDao<HotelResident> hotelResidentDao() {
    return new HotelResidentDao();
  }

  @Bean
  @Scope("singleton")
  public IGenericDao<HotelRoom> hotelRoomDao() {
    return new HotelRoomDao();
  }

  @Bean
  @Scope("singleton")
  public IGenericDao<RegistrationCard> registrationCardDao() {
    return new RegistrationCardDao();
  }

  @Bean
  @Scope("singleton")
  public IGenericDao<Service> serviceDao() {
    return new ServiceDao();
  }


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
    Resource[] resources = new ClassPathResource[ ]
            { new ClassPathResource( "springSources.properties" ) };
    pspc.setLocations( resources );
    pspc.setIgnoreUnresolvablePlaceholders( true );
    return pspc;
  }
}
