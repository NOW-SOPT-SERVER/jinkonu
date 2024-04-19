package org.sopt.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    }
}
