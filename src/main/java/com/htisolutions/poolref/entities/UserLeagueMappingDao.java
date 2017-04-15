package com.htisolutions.poolref.entities;


import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

@Transactional
public interface UserLeagueMappingDao extends CrudRepository<UserLeagueMapping, Long> {

    Iterable<UserLeagueMapping> findAllByUserId(Long userId);

    Iterable<UserLeagueMapping> findAllByLeagueId(Long leagueId);

}
