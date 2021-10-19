package com.slasher.springauth2.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "jms.jwt")
public class JwtIOProperties {

  private Security security;
  private String timezone;
  private String issuer;
  private Token token;
  private Exclude exclude;

  /**
   * ayuda a controlar si la seguridad va a estar activa o inactiva
   */
  @Data
  public static class Security {
    private boolean enabled;
  }

  @Data
  public static class Token {
    private Auth auth;
    private String secret;
    private int expiresIn;
  }

  @Data
  public static class Auth {
    private String path;
  }

  @Data
  private static class Exclude {
    private String path;
  }

}
