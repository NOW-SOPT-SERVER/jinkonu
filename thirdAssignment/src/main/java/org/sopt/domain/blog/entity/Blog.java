package org.sopt.domain.blog.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.domain.member.entity.Member;
import org.sopt.domain.post.entity.Post;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToMany(mappedBy = "blog")
    private List<Post> posts = new ArrayList<>();

    @Builder
    private Blog(String description, Member member, String title) {
        this.description = description;
        this.member = member;
        this.title = title;
    }
}
