package com.emlebi.restwebservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.emlebi.restwebservices.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  User findByUsername(String username);

}
