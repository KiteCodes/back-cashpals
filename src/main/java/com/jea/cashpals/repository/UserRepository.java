package com.jea.cashpals.repository;

import com.jea.cashpals.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findUserById(Integer id);

}
