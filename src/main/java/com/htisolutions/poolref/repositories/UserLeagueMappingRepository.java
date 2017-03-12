package com.htisolutions.poolref.repositories;

import com.htisolutions.poolref.entities.*;
import com.htisolutions.poolref.models.LeagueModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class UserLeagueMappingRepository {


    private UserDao userDao;

    private LeagueDao leagueDao;

    private UserLeagueMappingDao userLeagueMappingDao;

    @Autowired
    UserLeagueMappingRepository(
            UserDao userDao,
            LeagueDao leagueDao,
            UserLeagueMappingDao userLeagueMappingDao
    ) {
        this.userDao = userDao;
        this.leagueDao = leagueDao;
        this.userLeagueMappingDao = userLeagueMappingDao;
    }

    public Iterable<LeagueModel> findAllLeaguesByUserId(Long userId){
        List<LeagueModel> leagues = new ArrayList<>();
        Iterable<UserLeagueMapping> mappings = userLeagueMappingDao.findAllByUserId(userId);

        for (UserLeagueMapping mapping : mappings) {
            League league = leagueDao.findOne(mapping.getLeagueId());
            Iterable<User> users = findAllUsersByLeagueId(league.getId());

            leagues.add(new LeagueModel(league, users));
        }

        return leagues;
    }

    public Iterable<User> findAllUsersByLeagueId(Long leagueId) {
        List<User> users = new ArrayList<>();
        Iterable<UserLeagueMapping> mappings = userLeagueMappingDao.findAllByLeagueId(leagueId);

        for (UserLeagueMapping mapping : mappings) {
            User user = userDao.findOne(mapping.getUserId());
            users.add(user);
        }

        return users;
    }

    public void saveLeague(LeagueModel leagueModel) {
        League league = leagueDao.save(leagueModel.getLeague());

        List<UserLeagueMapping> mappings = new ArrayList<>();
        for (User user : leagueModel.getUsers()) {
            mappings.add(new UserLeagueMapping(league.getId(), user.getId()));
        }

        userLeagueMappingDao.save(mappings);
    }
}
