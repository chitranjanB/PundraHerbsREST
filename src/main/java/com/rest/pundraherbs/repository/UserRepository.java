package com.rest.pundraherbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.pundraherbs.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
