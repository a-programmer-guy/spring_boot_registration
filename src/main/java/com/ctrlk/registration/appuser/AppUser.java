package com.ctrlk.registration.appuser;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Lombok annotation for getters and setters
 */

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
/*
 * Auto generate UserDetails methods - Right click - source - implement methods
 * @Id annotation from persistence package so we can change the provider easily
 */
public class AppUser implements UserDetails {
	
	@SequenceGenerator(
			name = "registration_sequence", 
			sequenceName = "registration_sequence",
			allocationSize = 1
			)
	@Id
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "registration_sequence"
		)
	private Long id;
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String password;
	/*
	 * Enumerated specifies a persistent property of field that should be persisted
	 * as enumerated type. Represents a particular column in the DB
	 */
	@Enumerated(EnumType.STRING)
	private AppUserRole appUserRole;
	private Boolean locked = false;
	private Boolean enabled = false;
	
	/*
	 * Autogenerate constructor without Id field - will be generated for us
	 */	
	
	public AppUser(String firstName,
			String lastName,
			String username,
			String email,
			String password, 
			AppUserRole appUserRole) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.appUserRole = appUserRole;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
		return Collections.singletonList(authority);
	}
	
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}
