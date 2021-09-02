package com.senlaCourses.adPlacementSystem.domain.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.senlaCourses.adPlacementSystem.config.ApplicationContextConfig;
import javax.servlet.ServletContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationContextConfig.class})
@WebAppConfiguration(value = "com.senlaCourses.adPlacementSystem.config.WebApplicationContextConfig")
class RegistrationAndLoginControllerTest {

  @Autowired
  private WebApplicationContext context;
  private MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
  }

  @Test
  public void givenWac_whenServletContext_thenItProvidesAdController() {
    ServletContext servletContext = context.getServletContext();

    assertNotNull(servletContext);
    Assertions.assertTrue(servletContext instanceof MockServletContext);
    assertNotNull(context.getBean("registrationAndLoginController"));
  }

  @Test
  @Sql(value = {"/sql/clear-db.sql",
      "/sql/insert-roles-db.sql"},
      executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
  void addNewUser() throws Exception {
    String username = "abc";
    String email = "abc@mail.ru";
    String password = "password";

    this.mockMvc.perform(post("/registration")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"username\": \"" + username +
            "\", \"email\": \"" + email +
            "\", \"password\": \"" + password + "\" }")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated());
  }

  @Test
  @Sql(value = "/sql/clear-db.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
  void login() throws Exception {
    String username = "abc";
    String password = "password";

    MvcResult mvcResult = this.mockMvc.perform(post("/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"username\": \"" + username + "\", \"password\": \"" + password + "\" }")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();

    assertThat(mvcResult.getResponse().getContentAsString(), notNullValue());
  }
}
