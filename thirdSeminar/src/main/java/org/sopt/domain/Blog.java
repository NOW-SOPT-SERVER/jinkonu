package org.sopt.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.common.dto.request.BlogUpdateRequest;
import org.springframework.util.StringUtils;

import java.util.function.Consumer;

@Entity
@Getter
@NoArgsConstructor
public class Blog extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(length = 200)
    private String title;

    private String description;
    private String imageUrl;

    @Builder
    private Blog(String description, Member member, String title, String imageUrl) {
        this.description = description;
        this.member = member;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    private void updateTitle(String newTitle) {
        this.title = newTitle;
    }

    private void updateDescription(String newDescription) {
        this.description = newDescription;
    }

    private void updateIfNotBlank(String value, Consumer<String> updater) {
        if (StringUtils.hasText(value)) {
            updater.accept(value);
        }
    }

    public void update(BlogUpdateRequest updateRequest) {
        updateIfNotBlank(updateRequest.title(), this::updateTitle);
        updateIfNotBlank(updateRequest.description(), this::updateDescription);
    }
}
