package com.slasher.springauth2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ClientCredentialsException extends RuntimeException {

  public static ClientCredentialsException of() {
    return new ClientCredentialsException();
  }

  public ClientCredentialsException() {
    super("El campo grant type es invalido");
  }
}
