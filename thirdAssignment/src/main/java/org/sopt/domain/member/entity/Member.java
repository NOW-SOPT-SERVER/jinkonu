package org.sopt.domain.member.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.domain.blog.entity.Blog;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Enumerated(EnumType.STRING)
    private Part part;
    private int age;
    private LocalDateTime createdAt;
    private UUID token;

    @OneToOne(fetch = FetchType.LAZY)
    private Blog blog;

    public static Member create(String name, Part part, int age) {
        return Member.builder()
                .name(name)
                .part(part)
                .age(age)
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Builder
    public Member(int age, Part part, String name, LocalDateTime createdAt) {
        this.age = age;
        this.part = part;
        this.name = name;
        this.createdAt = createdAt;
        this.token = UUID.randomUUID();
    }

    public boolean matchesToken(String token) {
        return this.token.toString().equals(token);
    }
}
