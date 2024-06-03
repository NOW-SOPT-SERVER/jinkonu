package org.sopt.common.auth.redis.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(timeToLive = 60 * 60 * 24 * 1000L * 14)
@AllArgsConstructor
@Builder
@Getter
public class Token {

    @Id
    private Long memberId;

    @Indexed
    private String refreshToken;
}
