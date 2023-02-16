package com.draxter.draxter.Service.Token;

import com.draxter.draxter.Entity.SecureToken;

public interface ISecureTokenService {

    SecureToken createSecureToken();

    void saveSecureToken(final SecureToken token);

    SecureToken findByToken(final String token);

    void removeToken(final SecureToken token);

    void removeTokenByToken(final String token);

}
