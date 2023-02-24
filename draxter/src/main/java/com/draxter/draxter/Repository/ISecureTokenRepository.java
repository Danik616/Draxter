package com.draxter.draxter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.draxter.draxter.Entity.SecureToken;

public interface ISecureTokenRepository extends JpaRepository<SecureToken, Long> {

    SecureToken findByToken(final String token);

    Long removeByToken(String token);
}
