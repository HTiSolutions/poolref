package com.htisolutions.poolref.entities;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by root on 24/02/2017.
 */
@Transactional
public interface SubLeagueDao extends CrudRepository<SubLeague, Long> {

}
