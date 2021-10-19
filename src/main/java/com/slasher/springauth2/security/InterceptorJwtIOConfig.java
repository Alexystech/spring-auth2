package com.slasher.springauth2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class InterceptorJwtIOConfig implements WebMvcConfigurer {

  @Value("${jms.jwt.security.enabled:false}")
  private boolean securityEnabled;

  private InterceptorJWTIO interceptorJWTIO;

  @Autowired
  public InterceptorJwtIOConfig(InterceptorJWTIO interceptorJWTIO) {
    this.interceptorJWTIO = interceptorJWTIO;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    if ( securityEnabled ) {
      registry.addInterceptor(interceptorJWTIO);
    }

  }
}
