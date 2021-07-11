package com.submissions.back_end.api.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

public class ProblemRequest {
    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class Create{
        private Long id;
        @NotNull(message = "input can not be null")
        private String input;
        @NotNull(message = "output can not be null")
        private String output;
        @NotNull(message = "active can not be null")
        private boolean active;
        @NotNull(message = "code can not be null")
        private String code;
        @NotNull(message = "title can not be null")
        private String title;
        @NotNull(message = "topic can not be null")
        private String topic;
        @NotNull(message = "content can not be null")
        private String content;
        @NotNull(message = "level can not be null")
        private String level;
        private Float cpu_time_limit;
        private Float cpu_extra_time;
        private Integer memory_limit;
        private Integer max_file_size;
    }
}
