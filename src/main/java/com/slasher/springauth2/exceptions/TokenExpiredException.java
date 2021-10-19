package com.slasher.springauth2.exceptions;

public class TokenExpiredException extends RuntimeException {

  public static TokenExpiredException of() {
    return new TokenExpiredException();
  }

  public TokenExpiredException() {
    super("token expired");
  }

}
