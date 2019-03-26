package com.teamapps.authserver.repository;

import com.teamapps.authserver.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mihai Alexandru
 * @date 24.11.2018
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User getByLogin(String login);

}
