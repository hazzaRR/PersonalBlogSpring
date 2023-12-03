package com.hazr.personalblog.repository;

import com.hazr.personalblog.model.Role;
import com.hazr.personalblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);



//    List<Role> findUserRoles(String username);
}
