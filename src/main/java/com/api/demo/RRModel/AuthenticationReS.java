package com.api.demo.RRModel;

public class AuthenticationReS {

  private String jwt;

  public AuthenticationReS(String jwt) {
    this.jwt = jwt;
  }

  public String getJwt() {
    return jwt;
  }

  public void setJwt(String jwt) {
    this.jwt = jwt;
  }
}
