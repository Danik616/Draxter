package com.draxter.draxter.Service;

import com.draxter.draxter.Exception.InvalidTokenException;
import com.draxter.draxter.Exception.UnkownIdentifierException;

public interface ICustomerAccountService {
    void forgottenPassword(final String userName) throws UnkownIdentifierException;

    void updatePassword(final String password, final String token)
            throws InvalidTokenException, UnkownIdentifierException;

}
