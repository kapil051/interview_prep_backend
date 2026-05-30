package com.interviewprep.backend.module.questions.blind75.dto.request;

import com.interviewprep.backend.module.questions.blind75.enums.Difficulty;
import com.interviewprep.backend.module.questions.blind75.enums.Pattern;
import com.interviewprep.backend.module.questions.blind75.enums.Topic;
import com.interviewprep.backend.module.questions.blind75.enums.VideoAvailability;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Blind75Request {

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Difficulty is required")
    private Difficulty difficulty;

    @NotNull(message = "Topic is required")
    private Topic topic;

    @NotNull(message = "Pattern is required")
    private Pattern pattern;

    @NotBlank(message = "Practice link is required")
    private String practiceLink;

    private String videoSolutionLink;

    @NotNull(message = "Video availability is required")
    private VideoAvailability videoAvailability;
}