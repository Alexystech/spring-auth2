package com.slasher.springauth2.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1.0")
public class JwtController {

  @GetMapping(path = "/get/version")
  public ResponseEntity<Object> getVersion() {
    return ResponseEntity.ok("version 1.0");
  }

}
