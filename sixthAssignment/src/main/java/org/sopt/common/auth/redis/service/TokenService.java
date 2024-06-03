package org.sopt.common.auth.redis.service;

import lombok.RequiredArgsConstructor;
import org.sopt.common.auth.redis.domain.Token;
import org.sopt.common.auth.redis.repository.TokenRepository;
import org.sopt.common.exception.NotFoundException;
import org.sopt.common.exception.message.ErrorMessage;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    public void save(Long memberId, String refreshToken) {
        tokenRepository.save(Token.builder()
                .memberId(memberId)
                .refreshToken(refreshToken)
                .build());
    }
}
