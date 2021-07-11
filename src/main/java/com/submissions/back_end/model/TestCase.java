package com.submissions.back_end.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbltestcase")
public class TestCase {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "problem_id")
    private Long problem;
    private String input;
    private String expect_output;
}
