package com.htisolutions.poolref.controllers;

import com.htisolutions.poolref.services.ResultsService;
import com.htisolutions.poolref.viewModels.ResultEntryViewModel;
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
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@WebMvcTest(ResultsController.class)
@WithMockUser
public class ResultsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ResultsService resultsService;

    @Test
    public void validateFormName() throws Exception {
        List<ResultEntryViewModel> results = new ArrayList<>();
        results.add(new ResultEntryViewModel(new Date().toString(), "bm15731", "gc15xxx"));
        given(resultsService.getResults()).willReturn(results);

        mvc.perform(MockMvcRequestBuilders.get("/results/"))
                .andExpect(status().isOk())
                .andExpect(view().name("views/results"));
    }
}
