package com.senlaCourses.adPlacementSystem.domain.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
class ProfileControllerTest {

  @Autowired
  private WebApplicationContext context;
  private MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
  }

  @Test
  public void givenWac_whenServletContext_thenItProvidesProfileController() {
    ServletContext servletContext = context.getServletContext();

    assertNotNull(servletContext);
    Assertions.assertTrue(servletContext instanceof MockServletContext);
    assertNotNull(context.getBean("profileController"));
  }

  @Test
  @WithUserDetails("fourth")
  void addNewProfile() throws Exception {
    String phoneNumber = "123456";

    this.mockMvc.perform(post("/profile")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"phoneNumber\": \"" + phoneNumber + "\" }")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated());
  }

  @Test
  void removeProfile() throws Exception {
    this.mockMvc.perform(delete("/profile"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void changePhoneNumber() throws Exception {
    String newPhoneNumber = "1234";

    this.mockMvc.perform(put("/profile")
        .contentType(MediaType.APPLICATION_JSON)
        .content(newPhoneNumber)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(jsonPath("$.username").value("first"))
        .andExpect(jsonPath("$.phoneNumber").value("1234"))
        .andExpect(status().isOk());
  }

  @Test
  void showProfileInformation() throws Exception {
    MvcResult mvcResult = this.mockMvc.perform(get("/profile").param("id", "1"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.username").value("first"))
        .andExpect(jsonPath("$.phoneNumber").value("123456"))
        .andReturn();

    assertThat(mvcResult.getResponse().getContentType(), is("application/json"));
    assertThat(mvcResult.getResponse().getContentAsString(), containsString("first@mail.ru"));
  }

  @Test
  @Sql(value = {"/sql/clear-db.sql",
      "/sql/insert-users-roles-profiles-db.sql",
      "/sql/insert-ads-db.sql"},
      executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
  void showHistoryOfAds() throws Exception {
    MvcResult mvcResult = this.mockMvc.perform(get("/profile/history").param("id", "2"))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();

    assertThat(mvcResult.getResponse().getContentType(), is("application/json"));
    assertThat(mvcResult.getResponse().getContentAsString(),
        containsString("\"description\":\"first description\""));
    assertThat(mvcResult.getResponse().getContentAsString(),
        containsString("\"description\":\"second description\""));
    assertThat(mvcResult.getResponse().getContentAsString(),
        containsString("\"description\":\"third description\""));
    assertThat(mvcResult.getResponse().getContentAsString(),
        containsString("\"description\":\"fourth description\""));
  }
}