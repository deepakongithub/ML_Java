package com.deepak.ml.dto;

import lombok.Data;

  import java.util.Set;

@Data
public class CreateUserRequest {

     private String username;
     private String fullName;
     private String password;
     private String rePassword;
    private Set<String> authorities;

}
