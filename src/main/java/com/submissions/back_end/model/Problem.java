package com.submissions.back_end.model;

import lombok.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tblproblem")
@Data
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin
public class Problem {
    @Id
    @GeneratedValue
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
    @OneToMany(mappedBy = "problem")
    private List<TestCase> test_case;
}
