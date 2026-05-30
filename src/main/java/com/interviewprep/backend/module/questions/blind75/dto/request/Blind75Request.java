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

    @NotBlank
    private String title;

    @NotNull
    private Difficulty difficulty;

    @NotNull
    private Topic topic;

    @NotNull
    private Pattern pattern;

    @NotBlank
    private String practiceLink;

    private String videoSolutionLink;

    @NotNull
    private VideoAvailability videoAvailability;
}