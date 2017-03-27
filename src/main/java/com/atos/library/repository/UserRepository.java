package com.atos.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atos.library.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
