package com.htisolutions.poolref.entities;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface LeagueDao extends CrudRepository<League, Long> {

}
