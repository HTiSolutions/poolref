package com.htisolutions.poolref.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import com.htisolutions.poolref.controllers.RegisterController;
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
public class RegisterControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RegisterController registerController;

    @Test
    public void validateFormName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/register/"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    /*@Test
    public void validateUnsuccessfulRegister() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get(registerController.register("Joe","Bloggs","jbloggs@gmail.com","123","124")+"/"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }*/

    /*@Test
    public void validateSuccessfulRegister() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get(registerController.register("Joe","Bloggs","jbloggs@gmail.com","123","123")+"/"))
                .andExpect(status().isOk())
                .andExpect(view().name("views/greeting"));
    }*/
}
