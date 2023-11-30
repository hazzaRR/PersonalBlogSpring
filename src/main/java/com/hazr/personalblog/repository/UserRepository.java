package com.hazr.personalblog.repository;

import com.hazr.personalblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
