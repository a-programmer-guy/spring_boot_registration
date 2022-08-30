package com.ctrlk.registration.registration.token;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.ManyToAny;

import com.ctrlk.registration.appuser.AppUser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*Getters, setters, noargsconstructor generated with lombok. Neccessrary to include the
@Entity annotation or will result in: Not a managed type error*/

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {
	@SequenceGenerator(
			name = "confirmation_token_sequence",
			sequenceName = "confirmation_token_sequence",
			allocationSize = 1
			)
	@Id
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "confirmation_token_sequence"
		)
	private Long id;
	
	@Column(nullable = false)
	private String token;
	
	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@Column(nullable = false)
	private LocalDateTime expiresAt;
	
	private LocalDateTime confirmedAt;
	
	// ManyToOne because a user can have many tokens
	@ManyToOne
	@JoinColumn(
			nullable = false,
			name = "app_user_id")
	
	private AppUser appUser;
	
	public ConfirmationToken(
			String token, 
			LocalDateTime createdAt,
			LocalDateTime expiresAt,
			AppUser appUser) {
		this.token = token;
		this.createdAt = createdAt;
		this.expiresAt = expiresAt;
		this.appUser = appUser;
	}
	
	
}
