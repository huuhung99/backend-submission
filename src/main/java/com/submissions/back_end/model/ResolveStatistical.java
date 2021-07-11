package com.submissions.back_end.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResolveStatistical {
    private Long id;
    private String user_name;
    private String password;
    private String first_name;
    private String last_name;
    private String address;
    private String email;
    private String phone;
    private String sex;
    private Long date_of_birth;
    private int sum;
}
