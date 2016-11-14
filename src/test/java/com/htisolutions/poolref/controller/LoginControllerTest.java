package com.htisolutions.poolref.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import com.htisolutions.poolref.controllers.LoginController;
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
public class LoginControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void validateFormName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/login/"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void validateLoginRequest() throws Exception {
        LoginController loginController = new LoginController();
        mvc.perform(MockMvcRequestBuilders.get(loginController.signIn("jbloggs@gmail.com","123")+"/"))
                .andExpect(status().isOk())
                .andExpect(view().name("greeting"));
    }

}
