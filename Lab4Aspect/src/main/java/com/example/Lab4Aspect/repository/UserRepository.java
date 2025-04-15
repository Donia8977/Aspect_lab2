package com.example.Lab4Aspect.repository;

import com.example.Lab4Aspect.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
