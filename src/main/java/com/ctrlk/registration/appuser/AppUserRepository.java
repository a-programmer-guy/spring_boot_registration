package com.ctrlk.registration.appuser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/*
Data access layer for app user
*/
@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	// Query db for appuser by email
	Optional<AppUser> findByEmail(String email);
	
	// Update users enabled attribute
	@Transactional
	@Modifying
	@Query("UPDATE AppUser a SET a.enabled = TRUE WHERE a.email = ?1")
	int enableAppUser(String email); 
}
