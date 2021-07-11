package com.submissions.back_end.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResolveRequest {
//    @NotNull(message = "user_id can not be null")
    private Long account_id;
//    @NotNull(message = "problem_id can not be null")
    private Long problem_id;
//    @NotNull(message = "source_code can not be null")
    private String source_code;
//    @NotNull(message = "The time submit can not be null")
    private Date submission_time;
//    @NotNull(message = "language can not be null")
    private Integer language_id;
//    @NotNull(message = "stdin can not be null")
    private String stdin;
    private String expected_output;
    private Float cpu_time_limit;
    private Integer memory_limit;
}
