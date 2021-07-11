package com.submissions.back_end.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProblemDto {
    private Long id;
    private String input;
    private String output;
    private Float cpu_time_limit;
    private Float cpu_extra_time;
    private Integer memory_limit;
    private Integer max_file_size;
    private boolean active;
    private String code;
    private String title;
    private String topic;
    private String content;
    private String level;
}
