package com.htisolutions.poolref.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GreetingControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void validateFormName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/greeting/"))
                .andExpect(status().isOk())
                .andExpect(view().name("greeting"))
                .andExpect(model().attribute("name", "World"));
    }

    @Test
    public void validateDefaultGreeting() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/greeting/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("name", "World"));
    }

    @Test
    public void validatePassedGreeting() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/greeting/").param("name", "Bradley"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("name", "Bradley"));
    }

    @Test
    public void validateInvalidParameterGreeting() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/greeting/").param("forename", "Bradley"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("name", "World"));
    }

}
