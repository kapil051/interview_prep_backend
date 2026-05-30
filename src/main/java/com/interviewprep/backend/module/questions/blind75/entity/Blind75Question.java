package com.interviewprep.backend.module.questions.blind75.entity;

import com.interviewprep.backend.module.questions.blind75.enums.Difficulty;
import com.interviewprep.backend.module.questions.blind75.enums.Pattern;
import com.interviewprep.backend.module.questions.blind75.enums.Topic;
import com.interviewprep.backend.module.questions.blind75.enums.VideoAvailability;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "blind75_questions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Blind75Question {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Topic topic;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Pattern pattern;

    @Column(nullable = false)
    private String practiceLink;

    @Column(nullable = true)
    private String videoSolutionLink;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VideoAvailability videoAvailability;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }
}