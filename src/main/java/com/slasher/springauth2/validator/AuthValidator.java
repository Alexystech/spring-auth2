package com.slasher.springauth2.validator;

import com.slasher.springauth2.exceptions.ClientCredentialsException;
import com.slasher.springauth2.exceptions.ClientIdAndSecretException;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Objects;

@Component
public class AuthValidator {

  private static final String CLIENT_CREDENTIALS = "client_credentials";

  public void validate(MultiValueMap<String, String> paramMap, String grantType) {

    if ( !grantType.equals(CLIENT_CREDENTIALS) ) {
      throw ClientCredentialsException.of();
    }

    if ( Objects.isNull(paramMap) ||
        Objects.requireNonNull(paramMap.getFirst("client_id")).isEmpty() ||
        Objects.requireNonNull(paramMap.getFirst("client_secret")).isEmpty()) {
      throw ClientIdAndSecretException.of();
    }

  }

}
