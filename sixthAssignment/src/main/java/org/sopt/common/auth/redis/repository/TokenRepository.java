package org.sopt.common.auth.redis.repository;

import org.sopt.common.auth.redis.domain.Token;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TokenRepository extends CrudRepository<Token, Long> {

    Optional<Token> findByMemberId(Long memberId);
    Optional<Token> findByMemberIdAndRefreshToken(Long memberId, String refreshToken);
    Optional<Token> findByRefreshToken(String refreshToken);
}
