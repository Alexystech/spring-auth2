package com.slasher.springauth2.service.impl;

import com.slasher.springauth2.dto.JwtResponse;
import com.slasher.springauth2.dto.UserDTO;
import com.slasher.springauth2.security.JwtIO;
import com.slasher.springauth2.service.AuthService;
import com.slasher.springauth2.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

  private final JwtIO jwtIO;
  private final DateUtils dateUtils;

  @Value("${jms.jwt.token.expires-in:3600}")
  private int EXPIRES_IN;

  @Autowired
  public AuthServiceImpl(JwtIO jwtIO, DateUtils dateUtils) {
    this.jwtIO = jwtIO;
    this.dateUtils = dateUtils;
  }

  public JwtResponse login(String clientId, String clientSecret) {

    UUID uuid = UUID.randomUUID();

    UserDTO userDTO = UserDTO.builder()
        .uid(uuid.toString())
        .name("Alexis")
        .lastName("Vazquez")
        .role("ADMIN")
        .country("Mexico")
        .build();

    return JwtResponse.builder()
        .tokenType("beater")
        .accessToken(jwtIO.generateToken(userDTO))
        .issuedAt(dateUtils.getDateMillis() + "")
        .clientId(clientId)
        .expiresIn(EXPIRES_IN)
        .build();
  }

}
