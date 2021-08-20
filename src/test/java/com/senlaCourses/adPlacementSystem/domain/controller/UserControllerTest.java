package com.senlaCourses.adPlacementSystem.domain.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import org.springframework.security.test.context.support.WithUserDetails;
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
@ContextConfiguration(classes = ApplicationContextConfig.class)
@WebAppConfiguration(value = "com.senlaCourses.adPlacementSystem.config.WebApplicationContextConfig")
@WithUserDetails("first")
@Sql(value = {"/sql/clear-db.sql",
    "/sql/insert-users-roles-profiles-db.sql"},
    executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
class UserControllerTest {

  @Autowired
  private WebApplicationContext context;
  private MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
  }

  @Test
  public void givenWac_whenServletContext_thenItProvidesUserController() {
    ServletContext servletContext = context.getServletContext();

    Assertions.assertNotNull(servletContext);
    Assertions.assertTrue(servletContext instanceof MockServletContext);
    Assertions.assertNotNull(context.getBean("userController"));
  }

  @Test
  void changePassword() throws Exception {
    String currentPassword = "password";
    String newPassword = "new password";


    MvcResult mvcResult = this.mockMvc.perform(put("/user/password")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"currentPassword\": \"" + currentPassword +
            "\", \"newPassword\": \"" + newPassword + "\" }")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();

    assertThat(mvcResult.getResponse().getContentType(), is("application/json"));
  }

  @Test
  void changeEmail() throws Exception {
    String newEmail = "newFirst@mail.ru";

    MvcResult mvcResult = this.mockMvc.perform(put("/user/email")
        .contentType(MediaType.APPLICATION_JSON)
        .content(newEmail)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();

    assertThat(mvcResult.getResponse().getContentType(), is("application/json"));
    assertThat(
        mvcResult.getResponse().getContentAsString(),
        containsString("\"email\":\"newFirst@mail.ru\""));
  }
}