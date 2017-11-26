package com.intellect.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.intellect.user.domain.User;
@Component
public interface UserRepository extends JpaRepository<User, Long> {
	
}
