package com.htisolutions.poolref.controllers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.services.GameService;
import com.htisolutions.poolref.services.TwitterService;
import com.htisolutions.poolref.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(SubmitScoreController.class)
@WithMockUser
public class SubmitScoreControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private GameService gameService;

    @MockBean
    private TwitterService twitterService;

    @Test
    public void validateFormName() throws Exception {

        List<User> users = new ArrayList<>();
        users.add(new User("Bradley", "Miles", "bm15731", "$10$JUL48E.PCXBTETQh83gTZuX8ixwkSKFg41p7jfKxDT7fpuGXHykji"));

        given(userService.getUsers()).willReturn(users);

        mvc.perform(MockMvcRequestBuilders.get("/submit-score/"))
                .andExpect(status().isOk())
                .andExpect(view().name("views/submit-score"));
    }

}
