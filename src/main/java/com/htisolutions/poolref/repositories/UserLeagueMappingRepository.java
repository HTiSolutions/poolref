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
        if(users != null) {
          return users;
        }
        return null;
    }

    public void saveLeague(League league, List<Long> userIds) {
        leagueDao.save(league);

        List<UserLeagueMapping> mappings = new ArrayList<>();
        for (Long userId : userIds) {
            mappings.add(new UserLeagueMapping(league.getId(), userId));
        }

        userLeagueMappingDao.save(mappings);
    }

    public long saveLeague(String name) {
      League league = new League(name);
      try {
        leagueDao.save(league);
        return league.getId();
      }
      catch(Exception ex) {

      }
      return 0;
    }

    public Boolean addPlayers(long leagueId, long userId) {
      UserLeagueMapping mapping = new UserLeagueMapping(leagueId, userId);
      try {
        UserLeagueMapping savedMapping = userLeagueMappingDao.save(mapping);
        if (savedMapping != null) {
          return true;
        }
      }
      catch(Exception ex) {

      }
      return false;
    }

    public String getLeagueName(long id) {
      League league = leagueDao.findOne(id);
      if(league != null) {
        return league.getName();
      }
      return null;
    }


 }
