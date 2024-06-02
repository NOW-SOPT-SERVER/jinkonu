package org.sopt.common.auth;

import org.sopt.common.exception.UnauthorizedException;
import org.sopt.common.exception.message.ErrorMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class PrincipalHandler {

    private static final String ANONYMOUS_USER = "anonymousUser";

    public Long getUserIdFromPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (isPrincipalNull(principal))
            throw new UnauthorizedException(ErrorMessage.JWT_UNAUTHORIZED_EXCEPTION);

        return Long.valueOf(principal.toString());
    }

    public boolean isPrincipalNull(final Object principal) {
        return principal.toString().equals(ANONYMOUS_USER);
    }
}
