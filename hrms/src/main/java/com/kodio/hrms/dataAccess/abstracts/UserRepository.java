package com.kodio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodio.hrms.entities.concretes.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User getByEmail(String email);
	
	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);
	
	User getByUsername(String username);
	
	User getByVerificationCode(String code);
		
}
