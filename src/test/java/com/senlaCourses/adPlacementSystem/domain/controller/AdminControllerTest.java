package com.senlaCourses.adPlacementSystem.domain.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.senlaCourses.adPlacementSystem.config.ApplicationContextConfig;
import javax.servlet.ServletContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationContextConfig.class)
@WebAppConfiguration(value = "com.senlaCourses.adPlacementSystem.config.WebApplicationContextConfig")
@WithUserDetails("firstAdmin")
@Sql(value = {"/sql/clear-db.sql",
    "/sql/insert-admin-test-data.sql"},
    executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
class AdminControllerTest {

  @Autowired
  private WebApplicationContext context;
  private MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
  }

  @Test
  public void givenWac_whenServletContext_thenItProvidesAdminController() {
    ServletContext servletContext = context.getServletContext();

    Assertions.assertNotNull(servletContext);
    Assertions.assertTrue(servletContext instanceof MockServletContext);
    Assertions.assertNotNull(context.getBean("adminController"));
  }

  @Test
  void removeAdFromDb() throws Exception {
    this.mockMvc.perform(delete("/admin/remove/ad").param("id", "1"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void removeUser() throws Exception {
    this.mockMvc.perform(delete("/admin/remove/user").param("id", "2"))
        .andDo(print())
        .andExpect(status().isOk());
  }
}