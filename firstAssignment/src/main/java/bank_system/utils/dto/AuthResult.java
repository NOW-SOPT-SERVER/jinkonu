package bank_system.utils.dto;

import bank_system.domain.Customer;

public record AuthResult (
        Customer customer,
        String message
) { }
