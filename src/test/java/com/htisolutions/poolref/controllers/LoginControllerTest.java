package com.htisolutions.poolref.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(LoginController.class)
@WithMockUser
public class LoginControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void validateFormName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/login/"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    /*@Test
    public void validateLoginRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(loginController.signIn("jbloggs@gmail.com","123")+"/"))
                .andExpect(status().isOk())
                .andExpect(view().name("views/greeting"));
    }*/

}
