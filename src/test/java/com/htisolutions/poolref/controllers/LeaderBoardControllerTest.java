package com.htisolutions.poolref.controllers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.services.LeaderBoardService;
import com.htisolutions.poolref.viewModels.LeaderBoardEntryViewModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(LeaderBoardController.class)
@WithMockUser
public class LeaderBoardControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LeaderBoardService leaderBoardService;

    @Test
    public void validateFormName() throws Exception {
        List<LeaderBoardEntryViewModel> leaderboard = new ArrayList<>();
        User user = new User("Bradley", "Miles", "bm15731", "password");
        LeaderBoardEntryViewModel entry = new LeaderBoardEntryViewModel(user);
        entry.addWin();
        entry.addLoss();
        entry.addWin();
        entry.setPosition(1);
        leaderboard.add(entry);

        given(leaderBoardService.calculateLeaderBoard()).willReturn(leaderboard);

        this.mvc.perform(MockMvcRequestBuilders.get("/leaderboard/"))
                .andExpect(status().isOk())
                .andExpect(view().name("views/leaderboard"));
    }

}
