package com.htisolutions.poolref.entities;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface UserDao extends CrudRepository<User, Long> {

    User findByNickname(String nickname);

}
