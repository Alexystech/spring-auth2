package com.slasher.springauth2.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class UserDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private String uid;
  private String name;
  private String lastName;
  private String role;
  private String country;

}
