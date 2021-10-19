package com.slasher.springauth2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ClientIdAndSecretException extends RuntimeException {

  public static ClientIdAndSecretException of() {
    return new ClientIdAndSecretException();
  }

  public ClientIdAndSecretException() {
    super("client id y/o client secret son invalidos");
  }

}
