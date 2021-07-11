package com.submissions.back_end.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "tblresolve")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resolve {
    @Id
    @GeneratedValue
    private Long id;
    private Long accountId;
    @ManyToOne
    @JoinColumn(name = "problem_id") // thông qua khóa ngoại id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Problem problem;
    private String status;
    private Float time;
    @Column(name = "submission_time")
    private Date submissionTime;
    private Integer memory;
    private String token;
    @Column(name = "complie_output")
    private String complieOutput;
    private String massage;
    @ManyToOne
    @JoinColumn(name="language_id") 
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Language language;
}
