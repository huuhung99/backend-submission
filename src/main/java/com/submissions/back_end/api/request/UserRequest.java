package com.submissions.back_end.api.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

public class UserRequest {
    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class Create{
        private Long id;
//        @NotNull(message = "user name can not be null")
        private String user_name;
//        @NotNull(message = "password can not be null")
        private String password;
//        @NotNull(message = "first name can not be null")
        private String first_name;
//        @NotNull(message = "last name can not be null")
        private String last_name;
        private String address;
        private String email;
        private String phone;
//        @NotNull(message = "sex name can not be null")
        private String sex;
        private String date_of_birth;
    }
}
