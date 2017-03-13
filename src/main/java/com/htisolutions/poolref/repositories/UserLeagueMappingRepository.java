package com.htisolutions.poolref.repositories;

import com.htisolutions.poolref.entities.*;
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

    public Iterable<League> findAllLeaguesByUserId(Long userId){
        List<League> leagues = new ArrayList<>();
        Iterable<UserLeagueMapping> mappings = userLeagueMappingDao.findAllByUserId(userId);

        for (UserLeagueMapping mapping : mappings) {
            League league = leagueDao.findOne(mapping.getLeagueId());
            leagues.add(league);
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

    public void saveLeague(League league, List<Long> userIds) {
        leagueDao.save(league);

        List<UserLeagueMapping> mappings = new ArrayList<>();
        for (Long userId : userIds) {
            mappings.add(new UserLeagueMapping(league.getId(), userId));
        }

        userLeagueMappingDao.save(mappings);
    }
}
