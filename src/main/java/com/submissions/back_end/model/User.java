package com.submissions.back_end.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbluser")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
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
}
