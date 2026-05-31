package com.interviewprep.backend.module.questions.blind75.dto.response;

import com.interviewprep.backend.module.questions.blind75.enums.Difficulty;
import com.interviewprep.backend.module.questions.blind75.enums.Pattern;
import com.interviewprep.backend.module.questions.blind75.enums.ProgressStatus;
import com.interviewprep.backend.module.questions.blind75.enums.Topic;
import com.interviewprep.backend.module.questions.blind75.enums.VideoAvailability;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Blind75Response {

    private UUID id;
    private String title;
    private Difficulty difficulty;
    private Topic topic;
    private Pattern pattern;
    private String practiceLink;
    private String videoSolutionLink;
    private VideoAvailability videoAvailability;
    private ProgressStatus status;
}