package com.htisolutions.poolref.entities;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface GameDao extends CrudRepository<Game, Long> {

}
