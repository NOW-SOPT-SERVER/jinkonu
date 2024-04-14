package bank_system.utils.validate;

import bank_system.utils.exception.InvalidJoinException;

public class Validator {

    public static void validateJoin(String id, String password) {
        if (id.isEmpty() || password.isEmpty())
            throw new InvalidJoinException();
    }
}
