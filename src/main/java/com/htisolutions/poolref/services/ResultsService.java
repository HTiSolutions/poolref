package com.htisolutions.poolref.services;

import com.htisolutions.poolref.entities.Game;
import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.viewModels.ResultEntryViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class ResultsService {

    private GameService gameService;
    private UserService userService;

    @Autowired
    public ResultsService(GameService gameService, UserService userService) {
        this.gameService = gameService;
        this.userService = userService;
    }

    public Iterable<ResultEntryViewModel> getResults() {
        Iterable<Game> games = gameService.getGames();

        List<ResultEntryViewModel> results = new ArrayList<>();

        for (Game game : games) {
            String date = new SimpleDateFormat("dd MMM yyyy  HH:mm").format(game.getDate());
            User winner = userService.getUserById(game.getWinnerId());
            User loser = userService.getUserById(game.getLoserId());

            ResultEntryViewModel viewModel = new ResultEntryViewModel(
                date,
                winner.getNickname(),
                loser.getNickname()
            );
            results.add(viewModel);
        }
        Collections.reverse(results);
        return results;
    }

}
