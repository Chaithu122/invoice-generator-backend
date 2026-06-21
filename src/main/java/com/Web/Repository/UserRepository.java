package com.Web.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Web.Entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
  Optional<User> findByClerkId(String clerkId);
  boolean existsByClerkId(String clerkId);
	
}
