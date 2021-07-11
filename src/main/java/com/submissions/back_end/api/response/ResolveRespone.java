package com.submissions.back_end.api.response;

import com.submissions.back_end.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResolveRespone {
    private String stdout;
    private Float time;
    private Integer memory;
    private String stderr;
    private String token;
    private String compile_output;
    private String message;
    private Status status;
}
