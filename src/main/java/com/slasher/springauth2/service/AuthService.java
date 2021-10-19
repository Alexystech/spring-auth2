package com.slasher.springauth2.service;

import com.slasher.springauth2.dto.JwtResponse;

public interface AuthService {
  JwtResponse login(String clientId, String clientSecret);
}
