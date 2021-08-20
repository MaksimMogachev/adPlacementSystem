package com.senlaCourses.adPlacementSystem.domain.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
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
@ContextConfiguration(classes = {ApplicationContextConfig.class})
@WebAppConfiguration(value = "com.senlaCourses.adPlacementSystem.config.WebApplicationContextConfig")
@WithUserDetails("second")
@Sql(value = {"/sql/clear-db.sql",
    "/sql/insert-users-roles-profiles-db.sql",
    "/sql/insert-ads-db.sql"},
    executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
class AdControllerTest {

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

    Assertions.assertNotNull(servletContext);
    Assertions.assertTrue(servletContext instanceof MockServletContext);
    Assertions.assertNotNull(context.getBean("adController"));
  }

  @Test
  @WithUserDetails("first")
  @Sql(value = {"/sql/clear-db.sql",
      "/sql/insert-users-roles-profiles-db.sql"},
      executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
  void addNewAd() throws Exception {
    String nameOfAd = "nameOfAd";
    String description = "description";
    int price = 100;
    String city = "city";
    String category = "TRANSPORT";

    this.mockMvc.perform(post("/ads")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"nameOfAd\": \"" + nameOfAd +
            "\", \"description\": \"" + description +
            "\", \"price\": \"" + price +
            "\", \"city\": \"" + city +
            "\", \"category\": \"" + category + "\" }")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated());
  }

  @Test
  void changeAd() throws Exception {
    String nameOfAd = "nameOfAd";
    String description = "description";
    int price = 200;
    String city = "city";
    String category = "thing";

    this.mockMvc.perform(put("/ads/change").param("id", "2")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"nameOfAd\": \"" + nameOfAd +
            "\", \"description\": \"" + description +
            "\", \"price\": \"" + price +
            "\", \"city\": \"" + city +
            "\", \"category\": \"" + category + "\" }")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(jsonPath("$.nameOfAd").value("nameOfAd"))
        .andExpect(jsonPath("$.description").value("description"))
        .andExpect(jsonPath("$.price").value("200"))
        .andExpect(jsonPath("$.city").value("city"))
        .andExpect(jsonPath("$.category").value("THING"))
        .andExpect(status().isOk());
  }

  @Test
  void removeAd() throws Exception {
    this.mockMvc.perform(put("/ads/remove")
        .param("id", "1")
        .param("isSold", "true"))
        .andDo(print())
        .andExpect(jsonPath("$.active").value("false"))
        .andExpect(status().isOk());
  }

  @Test
  void payForAnAd() throws Exception {
    this.mockMvc.perform(put("/ads/pay")
        .param("id", "2")
        .param("isPaid", "true"))
        .andDo(print())
        .andExpect(jsonPath("$.paid").value("true"))
        .andExpect(status().isOk());
  }

  @Test
  void leaveComment() throws Exception {
    MvcResult mvcResult = this.mockMvc.perform(put("/ads/comment")
        .param("id", "2")
        .contentType(MediaType.APPLICATION_JSON)
        .content("first comment")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();

    MvcResult mvcResult2 = this.mockMvc.perform(put("/ads/comment")
        .param("id", "2")
        .contentType(MediaType.APPLICATION_JSON)
        .content("second comment")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();

    assertThat(mvcResult.getResponse().getContentType(), is("application/json"));
    assertThat(mvcResult.getResponse().getContentAsString(),
        containsString("first comment"));
    assertThat(mvcResult2.getResponse().getContentAsString(),
        containsString("second comment"));
  }
}