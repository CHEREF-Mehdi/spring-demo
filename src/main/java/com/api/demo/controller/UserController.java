package com.api.demo.controller;

import com.api.demo.RRModel.AuthenticationReQ;
import com.api.demo.RRModel.AuthenticationReS;
import com.api.demo.services.CustomUserDetailsService;
import com.api.demo.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private CustomUserDetailsService userDetailsService;

  @Autowired
  private JwtService jwtService;

  @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
  public ResponseEntity<?> authenticate(@RequestBody AuthenticationReQ body)
    throws Exception {
    try {
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          body.getUsername(),
          body.getPassword()
        )
      );
    } catch (Exception e) {
      throw new Exception("Incorrect Username or Password", e);
    }
    final UserDetails userDetails = userDetailsService.loadUserByUsername(
      body.getUsername()
    );
    final String jwt = jwtService.generateToken(userDetails);
    return ResponseEntity.ok(new AuthenticationReS(jwt));
  }
}
