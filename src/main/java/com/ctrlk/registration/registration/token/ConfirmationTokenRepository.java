package com.ctrlk.registration.registration.token;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long>{
	//List<ConfirmationToken> findByToken(String token);
	Optional<ConfirmationToken> findbyToken(String token);
}
