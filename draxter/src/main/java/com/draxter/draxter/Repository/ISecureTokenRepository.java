package com.draxter.draxter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.draxter.draxter.Entity.SecureToken;

@Repository
public interface ISecureTokenRepository extends JpaRepository<SecureToken, Long> {

    SecureToken findByToken(final String token);

    Long removeByToken(String token);
}
