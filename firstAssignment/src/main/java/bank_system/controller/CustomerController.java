package bank_system.controller;

import bank_system.utils.dto.AuthResult;
import bank_system.domain.Customer;
import bank_system.io.InputView;
import bank_system.io.OutputView;
import bank_system.repository.CustomerRepository;
import bank_system.utils.exception.InvalidLoginException;

public class CustomerController {

    private static final String JOIN_COMPLETED = "가입이 완료되었습니다";
    private static final String LOGIN_COMPLETED = "로그인이 완료되었습니다";

    public static AuthResult join() {
        String[] idAndPassword = InputView.readIdAndPassword();
        Customer customer = CustomerRepository.join(idAndPassword[0], idAndPassword[1]);

        return new AuthResult(customer, JOIN_COMPLETED);
    }

    public static AuthResult login() {
        try {
            String[] idAndPassword = InputView.readIdAndPassword();
            Customer customer = CustomerRepository.findByIdAndPassword(idAndPassword[0], idAndPassword[1])
                    .orElseThrow(InvalidLoginException::new);

            return new AuthResult(customer, LOGIN_COMPLETED);
        } catch (InvalidLoginException e) {
            OutputView.writeErrorMessageOf(e);

            return login();
        }
    }
}
