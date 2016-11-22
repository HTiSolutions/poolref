package com.htisolutions.poolref.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import com.htisolutions.poolref.controllers.LoginController;
import com.htisolutions.poolref.controllers.SubmitScoreController;
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
public class SubmitScoreControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void validateFormName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/submit-score/"))
                .andExpect(status().isOk())
                .andExpect(view().name("views/submit-score"));
    }

    @Test
    public void validateScoreRequest() throws Exception {
        SubmitScoreController submitscorecontroller = new SubmitScoreController();
        mvc.perform(MockMvcRequestBuilders.get(submitscorecontroller.submitScore("jbloggs@gmail.com","tbliggs@hotmail.com")+"/"))
                .andExpect(status().isOk())
                .andExpect(view().name("views/greeting"));
    }

}
