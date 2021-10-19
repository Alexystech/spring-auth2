package com.slasher.springauth2.api;

import com.slasher.springauth2.service.AuthService;
import com.slasher.springauth2.validator.AuthValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "v1.0")
public class AuthController {

  private final AuthService authService;
  private final AuthValidator authValidatior;

  @Autowired
  public AuthController(AuthService authService, AuthValidator authValidator) {
    this.authService = authService;
    this.authValidatior = authValidator;
  }

  @PostMapping(path = "oauth/client_credential/accesstoken",
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> login(@RequestBody MultiValueMap<String, String> paramMap,
                                      @RequestParam("grant_type") String grantType) {
    authValidatior.validate(paramMap, grantType);
    return ResponseEntity.ok(authService.login(paramMap.getFirst("client_id"),
        paramMap.getFirst("client_secret")));
  }

}
