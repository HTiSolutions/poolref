package com.htisolutions.poolref.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.htisolutions.poolref.services.GameService;
import com.htisolutions.poolref.services.ProfileService;
import com.htisolutions.poolref.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(ProfileController.class)
@WithMockUser
public class ProfileControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProfileService profileService;

    @MockBean
    private GameService gameService;

    @MockBean
    private UserService userService;

    @Test
    public void validateFormName() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/profile/"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("views/profile"));
    }

}
