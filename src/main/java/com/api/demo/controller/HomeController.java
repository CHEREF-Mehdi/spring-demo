package com.api.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HomeController {

  @Value("${server.port}")
  private String appPort;

  @GetMapping("")
  public String home() {
    return "Your app is running on port : " + appPort;
  }
}
